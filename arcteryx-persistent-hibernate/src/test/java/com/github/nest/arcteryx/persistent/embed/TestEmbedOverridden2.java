/**
 * 
 */
package com.github.nest.arcteryx.persistent.embed;

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
import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.AbstractPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.EmbeddablePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.EmbeddedPersistentColumn;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.internal.PersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.internal.PrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.internal.StandalonePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.HiloKey;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider;

/**
 * @author brad.wu
 */
public class TestEmbedOverridden2 {
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_PERSON(PERSON_ID BIGINT, PERSON_NAME VARCHAR(20), "
					+ "ADDRESS_LINE VARCHAR(100), ADDRESS_COUNTRY_NAME VARCHAR(30), ADDRESS_COUNTRY_ABBR VARCHAR(5), "
					+ "BORN_COUNTRY_NAME VARCHAR(30), BORN_COUNTRY_ABBR VARCHAR(5))");
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

		AbstractPersistentBeanDescriptor descriptor = createPersonDescriptor(context);
		createAddressDescriptor(context);
		createCountryDescriptor(context);
		context.afterContextInitialized();

		Person person = new Person();
		person.setName("test name");
		Country country = new Country();
		country.setName("China");
		country.setAbbr("CHN");
		Address address = new Address();
		address.setAddressLine("No.1 Renmin Rd. Shanghai");
		address.setCountry(country);
		person.setAddress(address);
		country = new Country();
		country.setName("American");
		country.setAbbr("US");
		person.setBornIn(country);

		IPersistentConfiguration configuration = context.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		sessionFactory.getCurrentSession().beginTransaction();
		descriptor.getSaver().save(person);
		sessionFactory.getCurrentSession().getTransaction().commit();

		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from T_PERSON");
			if (rst.next()) {
				assertEquals(101, rst.getLong("person_id"));
				assertEquals("test name", rst.getString("person_name"));
				assertEquals("China", rst.getString("ADDRESS_COUNTRY_NAME"));
				assertEquals("CHN", rst.getString("ADDRESS_COUNTRY_ABBR"));
				assertEquals("No.1 Renmin Rd. Shanghai", rst.getString("ADDRESS_LINE"));
				assertEquals("American", rst.getString("BORN_COUNTRY_NAME"));
				assertEquals("US", rst.getString("BORN_COUNTRY_ABBR"));
			}
			System.out.println("create TABLE:person OK");
			conn.close();
		}
	}

	private AbstractPersistentBeanDescriptor createPersonDescriptor(PersistentBeanDescriptorContext context) {
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
			EmbeddedPersistentColumn column = new EmbeddedPersistentColumn();
			column.setEmbeddedBeanClass(Address.class);
			// override again
			column.addOverriddenColumnName("country.name", "ADDRESS_COUNTRY_NAME");
			column.addOverriddenColumnName("country.abbr", "ADDRESS_COUNTRY_ABBR");
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("bornIn");
			EmbeddedPersistentColumn column = new EmbeddedPersistentColumn();
			column.setEmbeddedBeanClass(Country.class);
			// override
			column.addOverriddenColumnName("name", "BORN_COUNTRY_NAME");
			column.addOverriddenColumnName("abbr", "BORN_COUNTRY_ABBR");
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}

	private IPersistentBeanDescriptor createAddressDescriptor(PersistentBeanDescriptorContext context) {
		EmbeddablePersistentBeanDescriptor descriptor = new EmbeddablePersistentBeanDescriptor();
		descriptor.setBeanClass(Address.class);

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
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
			property.setName("country");
			EmbeddedPersistentColumn column = new EmbeddedPersistentColumn();
			column.setEmbeddedBeanClass(Country.class);
			// override
			column.addOverriddenColumnName("name", "COUNTRY_NAME");
			column.addOverriddenColumnName("abbr", "COUNTRY_ABBR");
			column.setPropertyDescriptor(property);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}

	private IPersistentBeanDescriptor createCountryDescriptor(PersistentBeanDescriptorContext context) {
		EmbeddablePersistentBeanDescriptor descriptor = new EmbeddablePersistentBeanDescriptor();
		descriptor.setBeanClass(Country.class);

		List<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("name");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("NAME");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		{
			PersistentBeanPropertyDescriptor property = new PersistentBeanPropertyDescriptor();
			property.setName("abbr");
			PrimitivePersistentColumn column = new PrimitivePersistentColumn();
			column.setName("ABBR");
			column.setType(PrimitiveColumnType.STRING);
			property.setPersistentColumn(column);
			properties.add(property);
		}

		descriptor.setProperties(properties);
		context.register(descriptor);
		return descriptor;
	}
}
