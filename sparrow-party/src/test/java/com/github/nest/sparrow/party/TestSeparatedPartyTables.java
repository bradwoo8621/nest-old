/**
 * 
 */
package com.github.nest.sparrow.party;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.ResourceDescriptorContextRepository;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentConfigurationInitializer;
import com.github.nest.goose.human.IGender;
import com.github.nest.goose.internal.Code;
import com.github.nest.goose.internal.human.Gender;
import com.github.nest.goose.internal.location.Country;
import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.internal.MyEmployee;
import com.github.nest.sparrow.party.internal.Individual;

/**
 * @author brad.wu
 */
public class TestSeparatedPartyTables {
	private static final String SPARROW_PARTY_CONTEXT_ID = "sparrow-party-context";
	private static final String GOOSE_CONTEXT_ID = "goose-context";

	@BeforeClass
	public static void initialize() throws Exception {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Context.createApplicationContextByClassPath(GOOSE_CONTEXT_ID, "/goose.xml");
			Context.createApplicationContextByClassPath(SPARROW_PARTY_CONTEXT_ID,
					"/com/github/nest/sparrow/party/sparrow-party-2tables.xml");
		}

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_PARTY(PARTY_ID BIGINT, "//
					+ "PARTY_TYPE VARCHAR(1), "//
					+ "PARTY_NAME VARCHAR(100), "//
					+ "PARTY_CODE VARCHAR(10), "//
					+ "PARTY_ENABLED INT, "//
					+ "CREATE_TIME TIMESTAMP, "//
					+ "CREATE_USER_ID BIGINT, "//
					+ "LAST_MODIFY_USER_ID BIGINT, "//
					+ "LAST_MODIFY_TIME TIMESTAMP, "//
					+ "OPTIMISTIC_LOCK BIGINT)");
			stat.execute("create table T_INDIVIDUAL(INDIVIDUAL_ID BIGINT, "//
					+ "ID_NUMBER VARCHAR(30), "//
					+ "FIRST_NAME VARCHAR(20), "//
					+ "MIDDLE_NAME VARCHAR(20), "//
					+ "LAST_NAME VARCHAR(20), "//
					+ "GENDER_CODE VARCHAR(1), "//
					+ "DATE_OF_BIRTH DATE, "//
					+ "DATE_OF_DEATH DATE, "//
					+ "BORN_IN_COUNTRY_CODE VARCHAR(3), "//
					+ "NATIONALITY_CODE VARCHAR(3))");
			stat.execute("create table T_ORGANIZATION(ORGANIZATION_ID BIGINT, "//
					+ "ID_NUMBER VARCHAR(30), "//
					+ "DATE_OF_BIRTH DATE, "//
					+ "DATE_OF_DEATH DATE, "//
					+ "BORN_IN_COUNTRY_CODE VARCHAR(3), "//
					+ "NATIONALITY_CODE VARCHAR(3), "//
					+ "INDUSTRY_CODE VARCHAR(3))");
			stat.execute("create sequence S_PARTY AS BIGINT start with 1");

			stat.execute("create table T_PARTY_ROLE(PARTY_ROLE_ID BIGINT, "//
					+ "PARTY_ROLE_CODE VARCHAR(10), "//
					+ "PARTY_ROLE_ENABLED INT, " //
					+ "PARTY_ROLE_TYPE VARCHAR(3), "//
					+ "PARTY_ID BIGINT, "//
					+ "CREATE_TIME TIMESTAMP, "//
					+ "CREATE_USER_ID BIGINT, "//
					+ "LAST_MODIFY_USER_ID BIGINT, "//
					+ "LAST_MODIFY_TIME TIMESTAMP, "//
					+ "OPTIMISTIC_LOCK BIGINT)");
			stat.execute("create sequence S_PARTY_ROLE AS BIGINT start with 1");

			stat.execute("create table T_EMPLOYEE(EMPLOYEE_ID BIGINT)");

			conn.commit();
			conn.close();
		}
	}

	@Test
	public void test() throws Exception {
		IResourceDescriptorContext goose = ResourceDescriptorContextRepository.getContext(GOOSE_CONTEXT_ID);
		ICachedBeanDescriptor countryDescriptor = goose.get(Country.class);
		ICachedBeanDescriptor genderDescriptor = goose.get(Gender.class);

		IResourceDescriptorContext sparrowParty = ResourceDescriptorContextRepository
				.getContext(SPARROW_PARTY_CONTEXT_ID);
		IPersistentBeanDescriptor employeeDescriptor = sparrowParty.get(MyEmployee.class);

		IPersistentConfiguration configuration = sparrowParty
				.getInitializedData(IPersistentConfigurationInitializer.KEY);

		SessionFactory sessionFactory = configuration.getRealConfiguration();
		long now = System.currentTimeMillis();

		{
			sessionFactory.getCurrentSession().beginTransaction();
			MyEmployee employee = new MyEmployee();
			employee.setParty(new Individual());
			employee.setPartyCode("PartyCode");

			employee.setIdNumber("ID123456");
			employee.setFirstName("First");
			employee.setMiddleName("Middle");
			employee.setLastName("Last");
			System.out.println(employee.getPartyName());

			employee.setBornIn((ICountry) countryDescriptor.getFromCache(new Code("CHN")));
			employee.setNationality((ICountry) countryDescriptor.getFromCache(new Code("USA")));

			employee.setGender((IGender) genderDescriptor.getFromCache(new Code("F")));

			employee.setDateOfBirth(DateUtils.parseDate("19800201", "yyyyMMdd"));
			employee.setDateOfDeath(DateUtils.parseDate("20501231", "yyyyMMdd"));

			OperateLog log = new OperateLog();
			log.setCreateTime(new Timestamp(now));
			log.setCreateUserId(100001l);
			log.setLastModifyTime(log.getCreateTime());
			log.setLastModifyUserId(log.getCreateUserId());
			employee.setPartyOperateLog(log);

			employee.setRoleCode("RoleCode");
			employee.setRoleEnabled(true);

			employeeDescriptor.getSaver().save(employee);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}

		{
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rst = stat.executeQuery("select * from T_PARTY");
			rst.next();
			assertEquals(1, rst.getLong("PARTY_ID"));
			assertEquals("PartyCode", rst.getString("PARTY_CODE"));
			assertEquals("First Middle Last", rst.getString("PARTY_NAME"));
			assertEquals("I", rst.getString("PARTY_TYPE"));
			assertEquals(Boolean.TRUE, rst.getBoolean("PARTY_ENABLED"));
			rst.close();
			rst = stat.executeQuery("SELECT * FROM T_INDIVIDUAL");
			rst.next();
			assertEquals(1, rst.getLong("INDIVIDUAL_ID"));
			assertEquals("ID123456", rst.getString("ID_NUMBER"));
			assertEquals("First", rst.getString("FIRST_NAME"));
			assertEquals("Middle", rst.getString("MIDDLE_NAME"));
			assertEquals("Last", rst.getString("LAST_NAME"));
			assertEquals("CHN", rst.getString("BORN_IN_COUNTRY_CODE"));
			assertEquals("USA", rst.getString("NATIONALITY_CODE"));
			assertEquals("F", rst.getString("GENDER_CODE"));
			assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_BIRTH")));
			assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_DEATH")));
			rst.close();
			rst = stat.executeQuery("SELECT * FROM T_PARTY_ROLE");
			rst.next();
			assertEquals(1, rst.getLong("PARTY_ROLE_ID"));
			assertEquals("RoleCode", rst.getString("PARTY_ROLE_CODE"));
			assertEquals("MEM", rst.getString("PARTY_ROLE_TYPE"));
			assertEquals(true, rst.getBoolean("PARTY_ROLE_ENABLED"));
			assertEquals(1, rst.getLong("PARTY_ID"));
			rst.close();
			rst = stat.executeQuery("SELECT * FROM T_EMPLOYEE");
			rst.next();
			assertEquals(1, rst.getLong("EMPLOYEE_ID"));
			rst.close();
			stat.close();
			conn.close();
		}

		{
			// will send select sql which join
			// T_PARTY/T_INDIVIDUAL/T_ORGANIZATION, maybe with performance
			// issue. 
			sessionFactory.getCurrentSession().beginTransaction();
			MyEmployee employee = employeeDescriptor.getLoader().load(1l);
			employee.getParty();

			assertEquals(Long.valueOf(1), employee.getPartyId());
			assertEquals("PartyCode", employee.getPartyCode());
			assertEquals("ID123456", employee.getIdNumber());
			assertEquals("First", employee.getFirstName());
			assertEquals("Middle", employee.getMiddleName());
			assertEquals("Last", employee.getLastName());
			assertEquals("First Middle Last", employee.getPartyName());
			assertEquals("CHN", employee.getBornIn().getCode());
			assertEquals("USA", employee.getNationality().getCode());
			assertEquals("F", employee.getGender().getCode());
			assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(employee.getDateOfBirth()));
			assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(employee.getDateOfDeath()));

			assertEquals(Long.valueOf(1), employee.getRoleId());
			assertEquals("RoleCode", employee.getRoleCode());
			assertEquals(true, employee.isRoleEnabled());
			assertEquals(Long.valueOf(1), employee.getPartyId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
	}
}
