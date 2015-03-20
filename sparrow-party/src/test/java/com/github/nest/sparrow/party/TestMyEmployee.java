/**
 * 
 */
package com.github.nest.sparrow.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.ResourceDescriptorContextRepository;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
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
import com.github.nest.sparrow.party.codes.IAcademicMajor;
import com.github.nest.sparrow.party.codes.IEducationDegree;
import com.github.nest.sparrow.party.codes.IJobTitle;
import com.github.nest.sparrow.party.codes.IOccupation;
import com.github.nest.sparrow.party.generalization.IEducationOrganization;
import com.github.nest.sparrow.party.generalization.IMyEmployee;
import com.github.nest.sparrow.party.internal.Address;
import com.github.nest.sparrow.party.internal.EducationExperience;
import com.github.nest.sparrow.party.internal.EducationOrganization;
import com.github.nest.sparrow.party.internal.Individual;
import com.github.nest.sparrow.party.internal.MyEmployee;
import com.github.nest.sparrow.party.internal.Organization;
import com.github.nest.sparrow.party.internal.WorkExperience;
import com.github.nest.sparrow.party.internal.codes.AcademicMajor;
import com.github.nest.sparrow.party.internal.codes.EducationDegree;
import com.github.nest.sparrow.party.internal.codes.JobTitle;
import com.github.nest.sparrow.party.internal.codes.Occupation;

/**
 * @author brad.wu
 */
public class TestMyEmployee {
	@BeforeClass
	public static void initialize() throws Exception {
		CaseInitializer.initLog();
		CaseInitializer.loadContext();
		CaseInitializer.create();
		CaseInitializer.increasePartySequence();
	}

	@Test
	public void test() throws Exception {
		IResourceDescriptorContext goose = ResourceDescriptorContextRepository
				.getContext(CaseInitializer.GOOSE_CONTEXT_ID);
		ICachedBeanDescriptor countryDescriptor = goose.get(Country.class);
		ICachedBeanDescriptor genderDescriptor = goose.get(Gender.class);

		IResourceDescriptorContext sparrowParty = ResourceDescriptorContextRepository
				.getContext(CaseInitializer.SPARROW_PARTY_CONTEXT_ID);
		IPersistentBeanDescriptor employeeDescriptor = sparrowParty.get(MyEmployee.class);
		IPersistentBeanDescriptor eduDescriptor = sparrowParty.get(EducationOrganization.class);

		IPersistentConfiguration configuration = sparrowParty
				.getInitializedData(IPersistentConfigurationInitializer.KEY);

		SessionFactory sessionFactory = configuration.getRealConfiguration();
		long now = System.currentTimeMillis();

		{
			sessionFactory.getCurrentSession().beginTransaction();
			IMyEmployee employee = createEmployee(goose, countryDescriptor, genderDescriptor, now);
			employeeDescriptor.getSaver().save(employee);

			IEducationOrganization edu = createEducationOrganization();
			eduDescriptor.getSaver().save(edu);

			IEducationExperience iee = new EducationExperience();
			iee.setOrganization(edu);
			iee.setStartDate(DateUtils.parseDate("20000901", "yyyyMMdd"));
			iee.setEndDate(DateUtils.parseDate("20060731", "yyyyMMdd"));
			iee.setAcademicMajor((IAcademicMajor) ((ICachedBeanDescriptor) sparrowParty.get(AcademicMajor.class))
					.getFromCache(new Code("CTS")));
			iee.setDegree((IEducationDegree) ((ICachedBeanDescriptor) sparrowParty.get(EducationDegree.class))
					.getFromCache(new Code("MST")));
			employee.setEducationExperiences(Arrays.asList(iee));

			IWorkExperience iwe = new WorkExperience();
			iwe.setEmployer(edu);
			iwe.setStartDate(DateUtils.parseDate("20060901", "yyyyMMdd"));
			iwe.setJobTitle((IJobTitle) ((ICachedBeanDescriptor) sparrowParty.get(JobTitle.class))
					.getFromCache(new Code("ANA")));
			iwe.setOccupation((IOccupation) ((ICachedBeanDescriptor) sparrowParty.get(Occupation.class))
					.getFromCache(new Code("MMO")));
			employee.setWorkExperiences(Arrays.asList(iwe));

			employeeDescriptor.getSaver().save(employee);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}

		{
			validateEmployeeBySQL();
			validateEducationOrganizationBySQL();
		}

		{
			sessionFactory.getCurrentSession().beginTransaction();
			validateEmployeeByDescriptor(sparrowParty);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
	}

	protected void validateEmployeeByDescriptor(IResourceDescriptorContext sparrowParty) throws ParseException {
		IPersistentBeanDescriptor employeeDescriptor = sparrowParty.get(MyEmployee.class);

		IMyEmployee employee = employeeDescriptor.getLoader().load(1l);

		assertEquals(Long.valueOf(11), employee.getPartyId());
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
		assertEquals(Long.valueOf(11), employee.getPartyId());

		List<IEducationExperience> eduList = employee.getEducationExperiences();
		assertEquals(1, eduList.size());
		IEducationExperience edu = eduList.get(0);
		assertEquals(Long.valueOf(1), edu.getExperienceId());
		assertEquals(DateUtils.parseDate("20000901", "yyyyMMdd"), edu.getStartDate());
		assertEquals(Long.valueOf(2), edu.getOrganization().getRoleId());
		assertEquals("MST", edu.getDegree().getCode());
		assertEquals("CTS", edu.getAcademicMajor().getCode());

		List<IWorkExperience> workList = employee.getWorkExperiences();
		assertEquals(1, workList.size());
		IWorkExperience work = workList.get(0);
		assertEquals(Long.valueOf(1), work.getExperienceId());
		assertEquals(DateUtils.parseDate("20060901", "yyyyMMdd"), work.getStartDate());
		assertEquals("ANA", work.getJobTitle().getCode());
		assertEquals("MMO", work.getOccupation().getCode());
		assertEquals(Long.valueOf(12), work.getEmployer().getPartyId());
		assertTrue(Organization.class.isAssignableFrom(work.getEmployer().getClass()));

		IEducationOrganization eduRole = ((IPersistentBeanDescriptor) sparrowParty.get(EducationOrganization.class))
				.getLoader().load(2l);
		assertTrue(eduRole.getParty() == work.getEmployer());
		assertEquals(Long.valueOf(2), eduRole.getRoleId());
		assertEquals("EDU001", eduRole.getPartyCode());
		assertEquals("Education 001", eduRole.getPartyName());
		assertEquals("EDUR001", eduRole.getRoleCode());
	}

	protected IMyEmployee createEmployee(IResourceDescriptorContext goose, ICachedBeanDescriptor countryDescriptor,
			ICachedBeanDescriptor genderDescriptor, long now) throws ParseException {
		IMyEmployee employee = new MyEmployee();
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
		address.setProvince((IProvince) ((ICachedBeanDescriptor) goose.get(Province.class))
				.getFromCache(new Code("SH")));
		address.setCity((ICity) ((ICachedBeanDescriptor) goose.get(City.class)).getFromCache(new Code("SH")));
		address.setDistrict((IDistrict) ((ICachedBeanDescriptor) goose.get(District.class))
				.getFromCache(new Code("HP")));
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

		return employee;
	}

	protected void validateEmployeeBySQL() throws SQLException, ParseException {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
		Statement stat = conn.createStatement();
		ResultSet rst = stat.executeQuery("select * from T_PARTY where PARTY_ID = 11");
		rst.next();
		assertEquals(11, rst.getLong("PARTY_ID"));
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
		assertEquals(11, rst.getLong("PARTY_ID"));
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
		assertEquals("MEM", rst.getString("PARTY_ROLE_TYPE"));
		assertEquals(true, rst.getBoolean("PARTY_ROLE_ENABLED"));
		assertEquals(11, rst.getLong("PARTY_ID"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_MY_EMPLOYEE");
		rst.next();
		assertEquals(1, rst.getLong("MY_EMPLOYEE_ID"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_PARTY_EDUCATION_EXPERIENCE");
		rst.next();
		assertEquals(1, rst.getLong("EDUCATION_EXPERIENCE_ID"));
		assertEquals(DateUtils.parseDate("20000901", "yyyyMMdd"), rst.getDate("START_DATE"));
		// role id
		assertEquals(2, rst.getLong("EDUCATION_ORGANIZATION_ID"));
		assertEquals("MST", rst.getString("DEGREE_CODE"));
		assertEquals("CTS", rst.getString("MAJOR_CODE"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_PARTY_WORK_EXPERIENCE");
		rst.next();
		assertEquals(1, rst.getLong("WORK_EXPERIENCE_ID"));
		assertEquals(DateUtils.parseDate("20060901", "yyyyMMdd"), rst.getDate("START_DATE"));
		assertEquals("ANA", rst.getString("JOB_TITLE_CODE"));
		assertEquals("MMO", rst.getString("OCCUPATION_CODE"));
		assertEquals(12, rst.getLong("EMPLOYER_PARTY_ID"));
		rst.close();
		stat.close();
		conn.close();
	}

	private IEducationOrganization createEducationOrganization() {
		EducationOrganization edu = new EducationOrganization();
		edu.setParty(new Organization());
		edu.setPartyCode("EDU001");
		edu.setPartyName("Education 001");
		edu.setRoleCode("EDUR001");
		return edu;
	}

	private void validateEducationOrganizationBySQL() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
		Statement stat = conn.createStatement();
		ResultSet rst = stat.executeQuery("select * from T_PARTY WHERE PARTY_ID = 12");
		rst.next();
		assertEquals(12, rst.getLong("PARTY_ID"));
		assertEquals("EDU001", rst.getString("PARTY_CODE"));
		assertEquals("O", rst.getString("PARTY_TYPE"));
		assertEquals("Education 001", rst.getString("PARTY_NAME"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_PARTY_ROLE WHERE PARTY_ROLE_ID = 2");
		rst.next();
		assertEquals(2, rst.getLong("PARTY_ROLE_ID"));
		assertEquals("EDUR001", rst.getString("PARTY_ROLE_CODE"));
		assertEquals("EDU", rst.getString("PARTY_ROLE_TYPE"));
		assertEquals(true, rst.getBoolean("PARTY_ROLE_ENABLED"));
		assertEquals(12, rst.getLong("PARTY_ID"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_EDUCATION_ORGANIZATION WHERE EDUCATION_ORGANIZATION_ID = 2");
		rst.next();
		assertEquals(2, rst.getLong("EDUCATION_ORGANIZATION_ID"));
		// return 0 when by getLong, so use getString instead
		// assertNull(rst.getString("PARENT_BRANCH_ID"));
		assertEquals(false, rst.getBoolean("IS_HEAD_OFFICE"));
		rst.close();
		stat.close();
		conn.close();
	}
}
