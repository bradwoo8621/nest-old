/**
 * 
 */
package com.github.nest.sparrow.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import com.github.nest.goose.internal.Code;
import com.github.nest.goose.internal.location.City;
import com.github.nest.goose.internal.location.Country;
import com.github.nest.goose.internal.location.District;
import com.github.nest.goose.internal.location.Province;
import com.github.nest.goose.location.ICity;
import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.location.IDistrict;
import com.github.nest.goose.location.IProvince;
import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.generalization.IMyBranch;
import com.github.nest.sparrow.party.internal.Address;
import com.github.nest.sparrow.party.internal.MyBranch;
import com.github.nest.sparrow.party.internal.Organization;

/**
 * @author brad.wu
 */
public class TestMyBranch {
	@BeforeClass
	public static void initialize() throws Exception {
		CaseInitializer.initLog();
		CaseInitializer.loadContext();
		CaseInitializer.create();
	}

	@Test
	public void test() throws Exception {
		IResourceDescriptorContext goose = ResourceDescriptorContextRepository.getContext(CaseInitializer.GOOSE_CONTEXT_ID);
		ICachedBeanDescriptor countryDescriptor = goose.get(Country.class);

		IResourceDescriptorContext sparrowParty = ResourceDescriptorContextRepository
				.getContext(CaseInitializer.SPARROW_PARTY_CONTEXT_ID);
		IPersistentBeanDescriptor myBranchDescriptor = sparrowParty.get(MyBranch.class);

		IPersistentConfiguration configuration = sparrowParty
				.getInitializedData(IPersistentConfigurationInitializer.KEY);

		SessionFactory sessionFactory = configuration.getRealConfiguration();
		long now = System.currentTimeMillis();

		{
			sessionFactory.getCurrentSession().beginTransaction();
			IMyBranch myBranch = createHeadOffice(goose, countryDescriptor, now);
			IMyBranch subBranch = createSubBranch(goose, countryDescriptor, now, myBranch);

			myBranchDescriptor.getSaver().save(myBranch);
			myBranchDescriptor.getSaver().save(subBranch);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}

		{
			validateHeadOfficeBySQL();
			validateSubBranchBySQL();
		}

		{
			sessionFactory.getCurrentSession().beginTransaction();
			IMyBranch headOffice = validateHeadOfficeByDescriptor(myBranchDescriptor);
			IMyBranch subBranch = validateSubBranchByDescriptor(myBranchDescriptor);
			assertEquals(headOffice, subBranch.getParentBranch());

			sessionFactory.getCurrentSession().getTransaction().commit();
		}
	}

	/**
	 * @param myBranchDescriptor
	 */
	protected IMyBranch validateHeadOfficeByDescriptor(IPersistentBeanDescriptor myBranchDescriptor) {
		IMyBranch myBranch = myBranchDescriptor.getLoader().load(1l);

		assertEquals(Long.valueOf(1), myBranch.getPartyId());
		assertEquals("PartyCode", myBranch.getPartyCode());
		assertEquals("ID123456", myBranch.getRegisterNumber());
		assertEquals("BranchName", myBranch.getPartyName());
		assertEquals("CHN", myBranch.getRegisterIn().getCode());
		assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(myBranch.getRegisterDate()));
		assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(myBranch.getCloseDownDate()));

		assertNotNull(myBranch.getPartyAddresses());
		assertEquals(1, myBranch.getPartyAddresses().size());
		IAddress address = myBranch.getPartyAddresses().get(0);
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

		assertEquals(Long.valueOf(1), myBranch.getRoleId());
		assertEquals("RoleCode", myBranch.getRoleCode());
		assertEquals(true, myBranch.isRoleEnabled());
		assertEquals(Long.valueOf(1), myBranch.getPartyId());
		assertEquals(true, myBranch.isHeadOffice());
		assertNull(myBranch.getParentBranch());

		return myBranch;
	}

	/**
	 * @param myBranchDescriptor
	 */
	protected IMyBranch validateSubBranchByDescriptor(IPersistentBeanDescriptor myBranchDescriptor) {
		IMyBranch myBranch = myBranchDescriptor.getLoader().load(2l);

		assertEquals(Long.valueOf(2), myBranch.getPartyId());
		assertEquals("SubCode", myBranch.getPartyCode());
		assertEquals("ID987654", myBranch.getRegisterNumber());
		assertEquals("SubBranchName", myBranch.getPartyName());
		assertEquals("CHN", myBranch.getRegisterIn().getCode());
		assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(myBranch.getRegisterDate()));
		assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(myBranch.getCloseDownDate()));

		assertNotNull(myBranch.getPartyAddresses());
		assertEquals(1, myBranch.getPartyAddresses().size());
		IAddress address = myBranch.getPartyAddresses().get(0);
		assertEquals(Long.valueOf(2), address.getAddressId());
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

		assertEquals(Long.valueOf(2), myBranch.getRoleId());
		assertEquals("RoleCode", myBranch.getRoleCode());
		assertEquals(true, myBranch.isRoleEnabled());
		assertEquals(Long.valueOf(2), myBranch.getPartyId());
		assertEquals(false, myBranch.isHeadOffice());
		assertNotNull(myBranch.getParentBranch());

		return myBranch;
	}

	/**
	 * @throws SQLException
	 */
	protected void validateHeadOfficeBySQL() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
		Statement stat = conn.createStatement();
		ResultSet rst = stat.executeQuery("select * from T_PARTY WHERE PARTY_ID = 1");
		rst.next();
		assertEquals(1, rst.getLong("PARTY_ID"));
		assertEquals("PartyCode", rst.getString("PARTY_CODE"));
		assertEquals("O", rst.getString("PARTY_TYPE"));
		assertEquals("ID123456", rst.getString("ID_NUMBER"));
		assertEquals("BranchName", rst.getString("PARTY_NAME"));
		assertEquals("CHN", rst.getString("BORN_IN_COUNTRY_CODE"));
		assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_BIRTH")));
		assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_DEATH")));
		rst.close();
		rst = stat.executeQuery("select * from T_PARTY_ADDRESS WHERE ADDRESS_ID = 1");
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
		rst = stat.executeQuery("SELECT * FROM T_PARTY_ROLE WHERE PARTY_ROLE_ID = 1");
		rst.next();
		assertEquals(1, rst.getLong("PARTY_ROLE_ID"));
		assertEquals("RoleCode", rst.getString("PARTY_ROLE_CODE"));
		assertEquals("MBR", rst.getString("PARTY_ROLE_TYPE"));
		assertEquals(true, rst.getBoolean("PARTY_ROLE_ENABLED"));
		assertEquals(1, rst.getLong("PARTY_ID"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_MY_BRANCH WHERE MY_BRANCH_ID = 1");
		rst.next();
		assertEquals(1, rst.getLong("MY_BRANCH_ID"));
		// return 0 when by getLong, so use getString instead
		assertNull(rst.getString("PARENT_BRANCH_ID"));
		assertTrue(rst.getBoolean("IS_HEAD_OFFICE"));
		rst.close();
		stat.close();
		conn.close();
	}

	/**
	 * @throws SQLException
	 */
	protected void validateSubBranchBySQL() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
		Statement stat = conn.createStatement();
		ResultSet rst = stat.executeQuery("select * from T_PARTY WHERE PARTY_ID = 2");
		rst.next();
		assertEquals(2, rst.getLong("PARTY_ID"));
		assertEquals("SubCode", rst.getString("PARTY_CODE"));
		assertEquals("O", rst.getString("PARTY_TYPE"));
		assertEquals("ID987654", rst.getString("ID_NUMBER"));
		assertEquals("SubBranchName", rst.getString("PARTY_NAME"));
		assertEquals("CHN", rst.getString("BORN_IN_COUNTRY_CODE"));
		assertEquals("19800201", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_BIRTH")));
		assertEquals("20501231", new SimpleDateFormat("yyyyMMdd").format(rst.getDate("DATE_OF_DEATH")));
		rst.close();
		rst = stat.executeQuery("select * from T_PARTY_ADDRESS WHERE ADDRESS_ID = 2");
		rst.next();
		assertEquals(2, rst.getLong("ADDRESS_ID"));
		assertEquals(2, rst.getLong("PARTY_ID"));
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
		rst = stat.executeQuery("SELECT * FROM T_PARTY_ROLE WHERE PARTY_ROLE_ID = 2");
		rst.next();
		assertEquals(2, rst.getLong("PARTY_ROLE_ID"));
		assertEquals("RoleCode", rst.getString("PARTY_ROLE_CODE"));
		assertEquals("MBR", rst.getString("PARTY_ROLE_TYPE"));
		assertEquals(true, rst.getBoolean("PARTY_ROLE_ENABLED"));
		assertEquals(2, rst.getLong("PARTY_ID"));
		rst.close();
		rst = stat.executeQuery("SELECT * FROM T_MY_BRANCH WHERE MY_BRANCH_ID = 2");
		rst.next();
		assertEquals(2, rst.getLong("MY_BRANCH_ID"));
		assertEquals(1, rst.getLong("PARENT_BRANCH_ID"));
		assertEquals(false, rst.getBoolean("IS_HEAD_OFFICE"));
		rst.close();
		stat.close();
		conn.close();
	}

	/**
	 * @param goose
	 * @param countryDescriptor
	 * @param now
	 * @return
	 * @throws ParseException
	 */
	protected IMyBranch createHeadOffice(IResourceDescriptorContext goose, ICachedBeanDescriptor countryDescriptor,
			long now) throws ParseException {
		IMyBranch myBranch = new MyBranch();
		myBranch.setParty(new Organization());
		myBranch.setPartyCode("PartyCode");

		myBranch.setRegisterNumber("ID123456");
		myBranch.setPartyName("BranchName");

		myBranch.setRegisterIn((ICountry) countryDescriptor.getFromCache(new Code("CHN")));

		myBranch.setRegisterDate(DateUtils.parseDate("19800201", "yyyyMMdd"));
		myBranch.setCloseDownDate(DateUtils.parseDate("20501231", "yyyyMMdd"));

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
		myBranch.setPartyAddresses(addresses);

		OperateLog log = new OperateLog();
		log.setCreateTime(new Timestamp(now));
		log.setCreateUserId(100001l);
		log.setLastModifyTime(log.getCreateTime());
		log.setLastModifyUserId(log.getCreateUserId());
		myBranch.setPartyOperateLog(log);

		myBranch.setRoleCode("RoleCode");
		myBranch.setRoleEnabled(true);
		myBranch.setHeadOffice(Boolean.TRUE);
		return myBranch;
	}

	/**
	 * @param goose
	 * @param countryDescriptor
	 * @param now
	 * @param parent
	 * @return
	 * @throws ParseException
	 */
	protected IMyBranch createSubBranch(IResourceDescriptorContext goose, ICachedBeanDescriptor countryDescriptor,
			long now, IMyBranch parent) throws ParseException {
		IMyBranch myBranch = new MyBranch();
		myBranch.setParty(new Organization());
		myBranch.setPartyCode("SubCode");

		myBranch.setRegisterNumber("ID987654");
		myBranch.setPartyName("SubBranchName");

		myBranch.setRegisterIn((ICountry) countryDescriptor.getFromCache(new Code("CHN")));

		myBranch.setRegisterDate(DateUtils.parseDate("19800201", "yyyyMMdd"));
		myBranch.setCloseDownDate(DateUtils.parseDate("20501231", "yyyyMMdd"));

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
		myBranch.setPartyAddresses(addresses);

		OperateLog log = new OperateLog();
		log.setCreateTime(new Timestamp(now));
		log.setCreateUserId(100001l);
		log.setLastModifyTime(log.getCreateTime());
		log.setLastModifyUserId(log.getCreateUserId());
		myBranch.setPartyOperateLog(log);

		myBranch.setRoleCode("RoleCode");
		myBranch.setRoleEnabled(true);
		myBranch.setParentBranch(parent);
		myBranch.setHeadOffice(Boolean.FALSE);
		return myBranch;
	}
}
