/**
 * 
 */
package com.github.nest.arcteryx.persistent.oneToOne;

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
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptorContext;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanLoader;
import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.OneToOnePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.OneToOneReversePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.internal.PrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.StandalonePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.ForeignKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.HiloKey;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentLoaderProvider;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider;

/**
 * @author brad.wu
 */
public class TestOneToOne {
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_PERSON(PERSON_ID BIGINT, PERSON_NAME VARCHAR(20))");
			stat.execute("create table T_ADDRESS(PERSON_ID BIGINT, ADDRESS_LINE VARCHAR(20))");
			stat.execute("create table HIBERNATE_UNIQUE_KEY(NEXT_HI INT)");
			stat.execute("INSERT INTO HIBERNATE_UNIQUE_KEY(NEXT_HI) VALUES (1)");
			conn.commit();
			System.out.println("create TABLE:person OK");
			conn.close();
		}

		PersistentBeanDescriptorContext context = new PersistentBeanDescriptorContext();
		context.setName("student");
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

		IPersistentBeanDescriptor studentDescriptor = createPersonDescriptor(context);
		createAddressDescriptor(context);
		context.afterContextInitialized();

		Person person = new Person();
		person.setName("Person");
		Address address = new Address();
		address.setAddressLine("AddressLine");
		address.setPerson(person);
		person.setAddress(address);

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		studentDescriptor.getSaver().save(person);
		sessionFactory.getCurrentSession().getTransaction().commit();

		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from T_PERSON");
			if (rst.next()) {
				assertEquals(101, rst.getLong("PERSON_ID"));
				assertEquals("Person", rst.getString("PERSON_NAME"));
			}
			rst.close();
			rst = stat.executeQuery("select * from T_ADDRESS");
			if (rst.next()) {
				assertEquals(101, rst.getLong("PERSON_ID"));
				assertEquals("AddressLine", rst.getString("ADDRESS_LINE"));
			}
			rst.close();
			conn.close();
		}

		sessionFactory.getCurrentSession().beginTransaction();
		person = studentDescriptor.getLoader().load(101l);
		assertEquals(101, person.getId().longValue());
		assertEquals("Person", person.getName());
		assertEquals(101, person.getAddress().getPersonId().longValue());
		assertEquals("AddressLine", person.getAddress().getAddressLine());
		assertEquals(person, person.getAddress().getPerson());
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

	private IPersistentBeanDescriptor createPersonDescriptor(PersistentBeanDescriptorContext context) {
		StandalonePersistentBeanDescriptor descriptor = new StandalonePersistentBeanDescriptor();
		descriptor.setBeanClass(Person.class);
		descriptor.setTableName("T_PERSON");

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("id");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("PERSON_ID");
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
			column.setName("PERSON_NAME");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("address");
			OneToOnePersistentColumn column = new OneToOnePersistentColumn();
			column.setSubordinateBeanClass(Address.class);
//			column.setCascadeTypes(new CascadeType[] { CascadeType.ALL });
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}

	private IPersistentBeanDescriptor createAddressDescriptor(BeanDescriptorContext context) {
		StandalonePersistentBeanDescriptor descriptor = new StandalonePersistentBeanDescriptor();
		descriptor.setBeanClass(Address.class);
		descriptor.setTableName("T_ADDRESS");

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("personId");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("PERSON_ID");
			column.setType(PrimitiveColumnType.LONG);
			column.setPrimaryKey(true);
			ForeignKey key = new ForeignKey();
			key.setProperty("person");
			column.setPrimaryKeyGenerator(key);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("addressLine");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("ADDRESS_LINE");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}
		
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("person");
			OneToOneReversePersistentColumn column = new OneToOneReversePersistentColumn();
			column.setParentBeanClass(Person.class);
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}

}
