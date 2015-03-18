/**
 * 
 */
package com.github.nest.sparrow.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import com.github.nest.goose.internal.location.City;
import com.github.nest.goose.internal.location.Country;
import com.github.nest.goose.internal.location.District;
import com.github.nest.goose.internal.location.Province;
import com.github.nest.goose.location.ICity;
import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.location.IDistrict;
import com.github.nest.goose.location.IProvince;
import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.internal.Address;
import com.github.nest.sparrow.party.internal.Employee;
import com.github.nest.sparrow.party.internal.Individual;

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
					+ "PARTY_TYPE VARCHAR(1), "//
					+ "PARTY_NAME VARCHAR(100), "//
					+ "PARTY_CODE VARCHAR(10), "//
					+ "PARTY_ENABLED INT, "//
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
					+ "INDUSTRY_CODE VARCHAR(3), "//
					+ "DATE_OF_BIRTH DATE, "//
					+ "DATE_OF_DEATH DATE, "//
					+ "BORN_IN_COUNTRY_CODE VARCHAR(3), "//
					+ "NATIONALITY_CODE VARCHAR(3))");
			stat.execute("create sequence S_PARTY AS BIGINT start with 1");

			stat.execute("create table T_PARTY_ADDRESS(ADDRESS_ID BIGINT, "//
					+ "PARTY_ID BIGINT, "//
					+ "POSTCODE VARCHAR(10), "//
					+ "DISTRICT_CODE VARCHAR(5), "//
					+ "CITY_CODE VARCHAR(5), "//
					+ "PROVINCE_CODE VARCHAR(5), "//
					+ "COUNTRY_CODE VARCHAR(3), "//
					+ "ADDRESS_LINE1 VARCHAR(30), "//
					+ "ADDRESS_LINE2 VARCHAR(30), "//
					+ "ADDRESS_LINE3 VARCHAR(30), "//
					+ "ADDRESS_LINE4 VARCHAR(30), "//
					+ "ADDRESS_LINE5 VARCHAR(30), "//
					+ "TELEPHONE VARCHAR(20), " //
					+ "FAX VARCHAR(20), " //
					+ "IS_ENABLED INT)");
			stat.execute("create sequence S_PARTY_ADDRESS AS BIGINT start with 1");

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
		IPersistentBeanDescriptor employeeDescriptor = sparrowParty.get(Employee.class);

		IPersistentConfiguration configuration = sparrowParty
				.getInitializedData(IPersistentConfigurationInitializer.KEY);

		SessionFactory sessionFactory = configuration.getRealConfiguration();
		long now = System.currentTimeMillis();

		{
			sessionFactory.getCurrentSession().beginTransaction();
			Employee employee = new Employee();
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

			List<IAddress> addresses = new ArrayList<IAddress>();
			Address address = new Address();
			address.setPostcode("100001");
			address.setCountry((ICountry) countryDescriptor.getFromCache(new Code("CHN")));
			address.setProvince((IProvince) ((ICachedBeanDescriptor) goose.get(Province.class)).getFromCache(new Code(
					"SH")));
			address.setCity((ICity) ((ICachedBeanDescriptor) goose.get(City.class)).getFromCache(new Code("SH")));
			address.setDistrict((IDistrict) ((ICachedBeanDescriptor) goose.get(District.class)).getFromCache(new Code(
					"HP")));
			address.setAddressLine1("line1");
			address.setAddressLine2("line2");
			address.setAddressLine3("line3");
			address.setAddressLine4("line4");
			address.setAddressLine5("line5");
			address.setTelephone("123456789");
			address.setFax("987654321");
			address.setEnabled(Boolean.TRUE);
			addresses.add(address);
			employee.setPartyAddresses(addresses);

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
			assertEquals("I", rst.getString("PARTY_TYPE"));
			assertEquals("ID123456", rst.getString("ID_NUMBER"));
			assertEquals("First", rst.getString("FIRST_NAME"));
			assertEquals("Middle", rst.getString("MIDDLE_NAME"));
			assertEquals("Last", rst.getString("LAST_NAME"));
			assertEquals("First Middle Last", rst.getString("PARTY_NAME"));
			assertEquals("CHN", rst.getString("BORN_IN_COUNTRY_CODE"));
			assertEquals("USA", rst.getString("NATIONALITY_CODE"));
			assertEquals("F", rst.getString("GENDER_CODE"));
			assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_BIRTH")));
			assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_DEATH")));
			rst.close();
			rst = stat.executeQuery("select * from T_PARTY_ADDRESS ORDER BY ADDRESS_ID");
			rst.next();
			assertEquals(1, rst.getLong("ADDRESS_ID"));
			assertEquals(1, rst.getLong("PARTY_ID"));
			assertEquals("100001", rst.getString("POSTCODE"));
			assertEquals("CHN", rst.getString("COUNTRY_CODE"));
			assertEquals("SH", rst.getString("PROVINCE_CODE"));
			assertEquals("SH", rst.getString("CITY_CODE"));
			assertEquals("HP", rst.getString("DISTRICT_CODE"));
			assertEquals("line1", rst.getString("ADDRESS_LINE1"));
			assertEquals("line2", rst.getString("ADDRESS_LINE2"));
			assertEquals("line3", rst.getString("ADDRESS_LINE3"));
			assertEquals("line4", rst.getString("ADDRESS_LINE4"));
			assertEquals("line5", rst.getString("ADDRESS_LINE5"));
			assertEquals("123456789", rst.getString("TELEPHONE"));
			assertEquals("987654321", rst.getString("FAX"));
			assertEquals(true, rst.getBoolean("IS_ENABLED"));
			rst.close();
			rst = stat.executeQuery("SELECT * FROM T_PARTY_ROLE");
			rst.next();
			assertEquals(1, rst.getLong("PARTY_ROLE_ID"));
			assertEquals("RoleCode", rst.getString("PARTY_ROLE_CODE"));
			assertEquals("EMP", rst.getString("PARTY_ROLE_TYPE"));
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
			sessionFactory.getCurrentSession().beginTransaction();
			Employee employee = employeeDescriptor.getLoader().load(1l);
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

			assertNotNull(employee.getPartyAddresses());
			assertEquals(1, employee.getPartyAddresses().size());
			IAddress address = employee.getPartyAddresses().get(0);
			assertEquals(Long.valueOf(1), address.getAddressId());
			assertEquals("100001", address.getPostcode());
			assertEquals("CHN", address.getCountry().getCode());
			assertEquals("SH", address.getProvince().getCode());
			assertEquals("SH", address.getCity().getCode());
			assertEquals("HP", address.getDistrict().getCode());
			assertEquals("line1", address.getAddressLine1());
			assertEquals("line2", address.getAddressLine2());
			assertEquals("line3", address.getAddressLine3());
			assertEquals("line4", address.getAddressLine4());
			assertEquals("line5", address.getAddressLine5());
			assertEquals("123456789", address.getTelephone());
			assertEquals("987654321", address.getFax());
			assertEquals(true, address.isEnabled());

			assertEquals(Long.valueOf(1), employee.getRoleId());
			assertEquals("RoleCode", employee.getRoleCode());
			assertEquals(true, employee.isRoleEnabled());
			assertEquals(Long.valueOf(1), employee.getPartyId());

			sessionFactory.getCurrentSession().getTransaction().commit();
		}
	}
}
