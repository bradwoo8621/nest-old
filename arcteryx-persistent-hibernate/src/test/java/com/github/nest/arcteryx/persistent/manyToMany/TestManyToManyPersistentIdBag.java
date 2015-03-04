/**
 * 
 */
package com.github.nest.arcteryx.persistent.manyToMany;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanLoader;
import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.IdBagCollectionParameter;
import com.github.nest.arcteryx.persistent.internal.ManyToManyPersistentColumn;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.internal.PrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.StandalonePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.HiloKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.UuidKey;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentLoaderProvider;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider;

/**
 * @author brad.wu
 *
 */
public class TestManyToManyPersistentIdBag {
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_STUDENT(STUDENT_ID BIGINT, STUDENT_NAME VARCHAR(20))");
			stat.execute("create table T_INTERMEDIATE(MID_STUDENT_ID BIGINT, MID_TEACHER_ID BIGINT, COLLECTION_ID VARCHAR(100))");
			stat.execute("create table T_TEACHER(TEACHER_ID BIGINT, TEACHER_NAME VARCHAR(20))");
			stat.execute("create table HIBERNATE_UNIQUE_KEY(NEXT_HI INT)");
			stat.execute("INSERT INTO HIBERNATE_UNIQUE_KEY(NEXT_HI) VALUES (1)");
			conn.commit();
			System.out.println("create TABLE:person OK");
			conn.close();
		}

		PersistentBeanDescriptorContext context = new PersistentBeanDescriptorContext();
		HibernatePersistentConfigurationInitializer initializer = new HibernatePersistentConfigurationInitializer();
		initializer.addProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
		initializer.addProperty("hibernate.connection.url", "jdbc:hsqldb:mem:memdb");
		initializer.addProperty("hibernate.connection.username", "username");
		initializer.addProperty("hibernate.connection.password", "password");
		initializer.addProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		initializer.addProperty("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
		initializer.addProperty("hibernate.show_sql", "true");
		initializer.addProperty("hibernate.connection.pool_size", "1");
		initializer.addProperty("hibernate.current_session_context_class", "thread");
		context.addConfigurationInitializer(initializer);
		context.getOperatorProviderRegistry().register(IPersistentBeanSaver.CODE,
				new HibernatePersistentSaverProvider());
		context.getOperatorProviderRegistry().register(IPersistentBeanLoader.CODE,
				new HibernatePersistentLoaderProvider());

		IPersistentBeanDescriptor studentDescriptor = createStudentDescriptor(context);
		IPersistentBeanDescriptor teacherDescriptor = createTeacherDescriptor(context);
		context.afterContextInitialized();

		List<Teacher> teacheres = new ArrayList<Teacher>();
		Teacher teacher = new Teacher();
		teacher.setName("Teacher1");
		teacheres.add(teacher);
		teacher = new Teacher();
		teacher.setName("Teacher2");
		teacheres.add(teacher);
		Student student = new Student();
		student.setName("Student");
		student.setTeacheres(teacheres);

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		for (Teacher t : teacheres) {
			teacherDescriptor.getSaver().save(t);
		}
		studentDescriptor.getSaver().save(student);
		sessionFactory.getCurrentSession().getTransaction().commit();

		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from T_STUDENT");
			rst.next();
			assertEquals(202, rst.getLong("STUDENT_ID"));
			assertEquals("Student", rst.getString("STUDENT_NAME"));
			rst.close();
			rst = stat.executeQuery("select * from T_TEACHER ORDER BY TEACHER_ID");
			rst.next();
			assertEquals(101, rst.getLong("TEACHER_ID"));
			assertEquals("Teacher1", rst.getString("TEACHER_NAME"));
			rst.next();
			assertEquals(102, rst.getLong("TEACHER_ID"));
			assertEquals("Teacher2", rst.getString("TEACHER_NAME"));
			if (rst.next()) {
				throw new RuntimeException("Exception raised.");
			}
			rst.close();
			rst = stat.executeQuery("select * from T_INTERMEDIATE ORDER BY MID_TEACHER_ID");
			rst.next();
			assertEquals(101, rst.getLong("MID_TEACHER_ID"));
			assertEquals(202, rst.getLong("MID_STUDENT_ID"));
			System.out.println(rst.getString("COLLECTION_ID"));
			rst.next();
			assertEquals(102, rst.getLong("MID_TEACHER_ID"));
			assertEquals(202, rst.getLong("MID_STUDENT_ID"));
			System.out.println(rst.getString("COLLECTION_ID"));
			if (rst.next()) {
				throw new RuntimeException("Exception raised.");
			}
			rst.close();
			conn.close();
		}

		sessionFactory.getCurrentSession().beginTransaction();
		student = studentDescriptor.getLoader().load(202l);
		assertEquals(202, student.getId().longValue());
		assertEquals("Student", student.getName());
		List<Teacher> teachers = student.getTeacheres();
		assertEquals(2, teachers.size());
		for (Teacher t : teacheres) {
			if (t.getId() == 101l) {
				assertEquals("Teacher1", t.getName());
			} else if (t.getId() == 102l) {
				assertEquals("Teacher2", t.getName());
			} else {
				throw new RuntimeException("exception raised.");
			}
		}
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		student.getTeacheres().remove(0);
		studentDescriptor.getSaver().save(student);
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

	private IPersistentBeanDescriptor createStudentDescriptor(PersistentBeanDescriptorContext context) {
		StandalonePersistentBeanDescriptor descriptor = new StandalonePersistentBeanDescriptor();
		descriptor.setBeanClass(Student.class);
		descriptor.setTableName("T_STUDENT");

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("id");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("STUDENT_ID");
			column.setType(PrimitiveColumnType.LONG);
			column.setPrimaryKey(true);
			HiloKey key = new HiloKey();
			column.setPrimaryKeyGenerator(key);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("name");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("STUDENT_NAME");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("teacheres");
			ManyToManyPersistentColumn column = new ManyToManyPersistentColumn();
			column.setReferencedBeanClass(Teacher.class);
			column.setIntermediateTableName("T_INTERMEDIATE");
			column.setForeignKeyColumnNameToMe("MID_STUDENT_ID");
			column.setForeignKeyColumnNameToRefer("MID_TEACHER_ID");
			IdBagCollectionParameter idbag = new IdBagCollectionParameter();
			idbag.setCollectionIdColumnName("COLLECTION_ID");
			idbag.setCollectionIdColumnType(PrimitiveColumnType.STRING);
			idbag.setCollectionIdGenerator(UuidKey.UUID);
			column.setCollectionParameter(idbag);
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}

	private IPersistentBeanDescriptor createTeacherDescriptor(PersistentBeanDescriptorContext context) {
		StandalonePersistentBeanDescriptor descriptor = new StandalonePersistentBeanDescriptor();
		descriptor.setBeanClass(Teacher.class);
		descriptor.setTableName("T_TEACHER");

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("id");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("TEACHER_ID");
			column.setType(PrimitiveColumnType.LONG);
			column.setPrimaryKey(true);
			HiloKey key = new HiloKey();
			column.setPrimaryKeyGenerator(key);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("name");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("TEACHER_NAME");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}
}
