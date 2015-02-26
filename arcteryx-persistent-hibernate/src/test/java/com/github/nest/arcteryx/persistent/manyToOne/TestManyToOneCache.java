/**
 * 
 */
package com.github.nest.arcteryx.persistent.manyToOne;

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
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.ManyToOnePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.internal.PrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.StandalonePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.HiloKey;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider;

/**
 * @author brad.wu
 *
 */
public class TestManyToOneCache {
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_STUDENT(STUDENT_ID BIGINT, STUDENT_NAME VARCHAR(20), MY_TEACHER_ID BIGINT)");
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

		IPersistentBeanDescriptor studentDescriptor = createStudentDescriptor(context);
		createTeacherDescriptor(context);
		context.afterContextInitialized();

		Teacher teacher = new Teacher();
		teacher.setId(1001l);
		teacher.setName("Teacher");
		Student student = new Student();
		student.setName("Student");
		student.setTeacher(teacher);

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		studentDescriptor.getSaver().save(student);
		sessionFactory.getCurrentSession().getTransaction().commit();

		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from T_STUDENT");
			if (rst.next()) {
				assertEquals(101, rst.getLong("STUDENT_ID"));
				assertEquals("Student", rst.getString("STUDENT_NAME"));
				assertEquals(1001, rst.getLong("MY_TEACHER_ID"));
			}
			rst.close();
			rst = stat.executeQuery("select * from T_TEACHER");
			assertEquals(false, rst.next());
			rst.close();
			System.out.println("create TABLE:person OK");
			conn.close();
		}
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
			property.setName("teacher");
			ManyToOnePersistentColumn column = new ManyToOnePersistentColumn();
			column.setReferencedBeanClass(Teacher.class);
			column.setForeignKeyColumnName("MY_TEACHER_ID");
			column.setForeignKeyPropertyName("id");
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}

	private IBeanDescriptor createTeacherDescriptor(PersistentBeanDescriptorContext context) {
		BeanDescriptor descriptor = new BeanDescriptor();
		descriptor.setBeanClass(Teacher.class);

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			BeanPropertyDescriptor property = new BeanPropertyDescriptor();
			property.setName("id");
			properties.add(property);
		}

		{
			BeanPropertyDescriptor property = new BeanPropertyDescriptor();
			property.setName("name");
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}
}
