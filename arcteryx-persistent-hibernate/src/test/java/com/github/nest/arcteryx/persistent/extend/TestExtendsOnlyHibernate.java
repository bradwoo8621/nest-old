/**
 * 
 */
package com.github.nest.arcteryx.persistent.extend;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;

/**
 * @author brad.wu
 */
public class TestExtendsOnlyHibernate {
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws ClassNotFoundException, SQLException {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_PARTY(PARTY_ID BIGINT, PARTY_NAME VARCHAR(100), PARTY_TYPE VARCHAR(1))");
			stat.execute("create table T_INDIVIDUAL(INDIVIDUAL_ID BIGINT, FIRST_NAME VARCHAR(20), MIDDLE_NAME VARCHAR(20), LAST_NAME VARCHAR(20))");
			stat.execute("create table T_ORGANIZATION(ORGANIZATION_ID BIGINT, ABBR VARCHAR(20))");
			stat.execute("create table HIBERNATE_UNIQUE_KEY(NEXT_HI INT)");
			stat.execute("INSERT INTO HIBERNATE_UNIQUE_KEY(NEXT_HI) VALUES (1)");
			conn.commit();
			System.out.println("create TABLE:person OK");
			conn.close();
		}

		Configuration configuration = new Configuration();
		Properties properties = new Properties();
		properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
		properties.put("hibernate.connection.url", "jdbc:hsqldb:mem:memdb");
		properties.put("hibernate.connection.username", "username");
		properties.put("hibernate.connection.password", "password");
		properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.put("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.connection.pool_size", "1");
		properties.put("hibernate.current_session_context_class", "thread");
		configuration.setProperties(properties);
		configuration.addURL(getClass().getResource("extends.xml"));

		Individual individual = new Individual();
		individual.setFirstName("first");
		individual.setMiddleName("middle");
		individual.setLastName("last");

		SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(individual);
		session.getTransaction().commit();

		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Individual party = (Individual) session.load(Individual.class, 101l);
		assertEquals("first", party.getFirstName());
		assertEquals("middle", party.getMiddleName());
		assertEquals("last", party.getLastName());
		session.getTransaction().commit();
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Individual> list = session.createQuery("from Party where PARTY_ID = :id").setLong("id", 101l).list();
		assertEquals(1, list.size());
		party = list.get(0);
		assertEquals("first", party.getFirstName());
		assertEquals("middle", party.getMiddleName());
		assertEquals("last", party.getLastName());
		session.getTransaction().commit();
	}
}
