/**
 * 
 */
package com.github.nest.arcteryx.persistent.first;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.AbstractPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.internal.PrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.SequenceKey;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider;

/**
 * @author brad.wu
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOptimisticTimestamp {
	private static PersistentBeanDescriptorContext context = null;
	private static AbstractPersistentBeanDescriptor descriptor = null;
	private static Timestamp ts = null;

	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException {
		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table person(PERSON_ID BIGINT, PERSON_NAME VARCHAR(20), VERSION TIMESTAMP)");
			stat.execute("create sequence HIBERNATE_SEQUENCE start with 1");
			// stat.execute("insert into person(person_id, person_name, version) values(1, 'test name', 1)");
			conn.commit();
			System.out.println("create TABLE:person OK");
			conn.close();
		}

		context = new PersistentBeanDescriptorContext();
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

		descriptor = new AbstractPersistentBeanDescriptor();
		descriptor.setBeanClass(Person.class);

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("id");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("PERSON_ID");
			column.setType(PrimitiveColumnType.LONG);
			column.setPrimaryKey(true);
			SequenceKey key = new SequenceKey();
			key.setSequenceName("HIBERNATE_SEQUENCE");
			column.setPrimaryKeyGenerator(key);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("name");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("PERSON_NAME");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("vts");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("VERSION");
			column.setType(PrimitiveColumnType.TIMESTAMP);
			column.setVersion(true);
			property.setPersistentColumn(column);
			properties.add(property);
		}
		descriptor.setProperties(properties);
		context.register(descriptor);

		context.afterContextInitialized();

		Person person = new Person();
		person.setName("test name");

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		descriptor.getSaver().save(person);
		sessionFactory.getCurrentSession().getTransaction().commit();
		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from person");
			if (rst.next()) {
				assertEquals(1, rst.getLong("person_id"));
				assertEquals("test name", rst.getString("person_name"));
				ts = rst.getTimestamp("version");
				assertTrue(ts.getTime() == person.getVts().getTime());
			}
			System.out.println("create TABLE:person OK");
			conn.close();
		}
	}

	@Test
	public void testUpdate() throws SQLException {
		Person person = new Person();
		person.setId(1l);
		person.setName("test name 1");
		person.setVts(ts);

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		descriptor.getSaver().update(person);
		sessionFactory.getCurrentSession().getTransaction().commit();
		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from person");
			if (rst.next()) {
				assertEquals(1, rst.getLong("person_id"));
				assertEquals("test name 1", rst.getString("person_name"));
				assertTrue(rst.getTimestamp("version").getTime() - ts.getTime() > 0);
				assertTrue(rst.getTimestamp("version").getTime() == person.getVts().getTime());
			}
			System.out.println("create TABLE:person OK");
			conn.close();
		}
	}

	@Test(expected = StaleObjectStateException.class)
	public void testUpdateLock() throws SQLException {
		Person person = new Person();
		person.setId(1l);
		person.setName("test name 1");
		person.setVts(new Timestamp(ts.getTime() + 1));

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		descriptor.getSaver().update(person);
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
}
