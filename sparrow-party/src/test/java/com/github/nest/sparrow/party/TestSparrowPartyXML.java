/**
 * 
 */
package com.github.nest.sparrow.party;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;

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
import com.github.nest.sparrow.party.internal.Employee;

/**
 * @author brad.wu
 */
public class TestSparrowPartyXML {
	private static final String SPARROW_PARTY_CONTEXT_ID = "sparrow-party-context";
	private static final String GOOSE_CONTEXT_ID = "goose-context";

	@BeforeClass
	public static void initialize() throws Exception {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.FATAL);
		Logger.getLogger(HibernatePersistentConfigurationInitializer.class).setLevel(Level.DEBUG);

		{
			Context.createApplicationContextByClassPath(GOOSE_CONTEXT_ID, "/goose.xml");
			Context.createApplicationContextByClassPath(SPARROW_PARTY_CONTEXT_ID, "/sparrow-party.xml");
		}

		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
			Statement stat = conn.createStatement();
			stat.execute("create table T_PARTY(PARTY_ID BIGINT, "//
					+ "PARTY_NAME VARCHAR(100), "//
					+ "PARTY_CODE VARCHAR(10), "//
					+ "CREATE_TIME TIMESTAMP, "//
					+ "CREATE_USER_ID BIGINT, "//
					+ "LAST_MODIFY_USER_ID BIGINT, "//
					+ "LAST_MODIFY_TIME TIMESTAMP, "//
					+ "OPTIMISTIC_LOCK BIGINT, "//
					+ "ID_NUMBER VARCHAR(30), "//
					+ "FIRST_NAME VARCHAR(20), "//
					+ "MIDDLE_NAME VARCHAR(20), "//
					+ "LAST_NAME VARCHAR(20), "//
					+ "GENDER_CODE VARCHAR(1), "//
					+ "DATE_OF_BIRTH DATE, "//
					+ "DATE_OF_DEATH DATE, "//
					+ "BORN_IN_COUNTRY_CODE VARCHAR(3), "//
					+ "NATIONALITY_CODE VARCHAR(3))");
			stat.execute("create sequence S_PARTY AS BIGINT");
			conn.commit();
			System.out.println("create TABLE:person OK");
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
		IPersistentBeanDescriptor employeeDescriptor = sparrowParty.get(IPartyConstants.DEFAULT_AS_PARTY_PREFIX
				+ ".Employee");

		IPersistentConfiguration configuration = sparrowParty
				.getInitializedData(IPersistentConfigurationInitializer.KEY);

		SessionFactory sessionFactory = configuration.getRealConfiguration();
		long now = System.currentTimeMillis();

		{
			sessionFactory.getCurrentSession().beginTransaction();
			Employee employee = new Employee();
			employee.setCode("PartyCode");

			employee.setIdNumber("ID123456");
			employee.setFirstName("First");
			employee.setMiddleName("Middle");
			employee.setLastName("Last");

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
			employee.setOperateLog(log);

			employeeDescriptor.getSaver().save(employee);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
	}
}
