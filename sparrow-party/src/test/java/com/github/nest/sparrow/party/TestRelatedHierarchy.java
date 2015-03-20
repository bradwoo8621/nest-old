/**
 * 
 */
package com.github.nest.sparrow.party;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.ResourceDescriptorContextRepository;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.sparrow.party.generalization.IEducationOrganization;
import com.github.nest.sparrow.party.generalization.IRelatedDepartment;
import com.github.nest.sparrow.party.generalization.IRelatedEmployee;
import com.github.nest.sparrow.party.internal.EducationOrganization;
import com.github.nest.sparrow.party.internal.Individual;
import com.github.nest.sparrow.party.internal.Organization;
import com.github.nest.sparrow.party.internal.RelatedDepartment;
import com.github.nest.sparrow.party.internal.RelatedEmployee;

/**
 * @author brad.wu
 */
public class TestRelatedHierarchy {
	@BeforeClass
	public static void initialize() throws Exception {
		CaseInitializer.initLog();
		CaseInitializer.loadContext();
		CaseInitializer.create();
		CaseInitializer.increasePartySequence();
	}

	@Test
	public void testHierarchy() {
		IResourceDescriptorContext sparrowParty = ResourceDescriptorContextRepository
				.getContext(CaseInitializer.SPARROW_PARTY_CONTEXT_ID);

		IPersistentBeanDescriptor branchDescriptor = sparrowParty.get(EducationOrganization.class);
		IPersistentBeanDescriptor deptDescriptor = sparrowParty.get(RelatedDepartment.class);
		IPersistentBeanDescriptor employeeDescriptor = sparrowParty.get(RelatedEmployee.class);

		IPersistentConfiguration configuration = sparrowParty
				.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();

		IEducationOrganization edu1 = this.createEductationOrganization1(branchDescriptor);
		IEducationOrganization edu2 = this.createEductationOrganization2(branchDescriptor, edu1);
		IRelatedDepartment dept1 = this.createDepartment(deptDescriptor, edu2);
		IRelatedDepartment dept2 = this.createDepartment(deptDescriptor, edu2, dept1);
		IRelatedEmployee emp = this.createEmployee(employeeDescriptor, edu2, dept2);

		sessionFactory.getCurrentSession().beginTransaction();
		branchDescriptor.getSaver().save(edu1);
		branchDescriptor.getSaver().save(edu2);
		deptDescriptor.getSaver().save(dept1);
		deptDescriptor.getSaver().save(dept2);
		employeeDescriptor.getSaver().save(emp);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		validate(branchDescriptor, deptDescriptor, employeeDescriptor);
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

	private void validate(IPersistentBeanDescriptor branchDescriptor, IPersistentBeanDescriptor deptDescriptor,
			IPersistentBeanDescriptor employeeDescriptor) {
		IEducationOrganization edu1 = branchDescriptor.getLoader().load(1l);
		assertEquals(Long.valueOf(1), edu1.getRoleId());
		assertEquals(Long.valueOf(11), edu1.getPartyId());
		assertEquals("PC_001", edu1.getPartyCode());
		assertEquals("RC_001", edu1.getRoleCode());
		assertEquals("School 001", edu1.getPartyName());

		IEducationOrganization edu2 = branchDescriptor.getLoader().load(2l);
		assertEquals(Long.valueOf(2), edu2.getRoleId());
		assertEquals(Long.valueOf(12), edu2.getPartyId());
		assertEquals("PC_002", edu2.getPartyCode());
		assertEquals("RC_002", edu2.getRoleCode());
		assertEquals("School 002", edu2.getPartyName());
		assertTrue(edu2.getParentBranch() == edu1.getParty());
		
		IRelatedDepartment dept1 = deptDescriptor.getLoader().load(3l);
		assertEquals(Long.valueOf(3), dept1.getRoleId());
		assertEquals(Long.valueOf(13), dept1.getPartyId());
		assertEquals("PC_003", dept1.getPartyCode());
		assertEquals("RC_003", dept1.getRoleCode());
		assertEquals("Security", dept1.getPartyName());
		assertTrue(dept1.getBranch() == edu2.getParty());
		
		IRelatedDepartment dept2 = deptDescriptor.getLoader().load(4l);
		assertEquals(Long.valueOf(4), dept2.getRoleId());
		assertEquals(Long.valueOf(14), dept2.getPartyId());
		assertEquals("PC_004", dept2.getPartyCode());
		assertEquals("RC_004", dept2.getRoleCode());
		assertEquals("Gate Security", dept2.getPartyName());
		assertTrue(dept2.getBranch() == edu2.getParty());
		assertTrue(dept2.getParentDepartment() == dept1);
		
		IRelatedEmployee emp = employeeDescriptor.getLoader().load(5l);
		assertEquals(Long.valueOf(5), emp.getRoleId());
		assertEquals(Long.valueOf(15), emp.getPartyId());
		assertEquals("PC_005", emp.getPartyCode());
		assertEquals("RC_005", emp.getRoleCode());
		assertEquals("Master Lee", emp.getPartyName());
		assertTrue(emp.getBranch() == edu2.getParty());
		assertTrue(emp.getDepartment() == dept2);
	}

	private IRelatedEmployee createEmployee(IPersistentBeanDescriptor employeeDescriptor,
			IEducationOrganization branch, IRelatedDepartment dept) {
		IRelatedEmployee emp = new RelatedEmployee();
		emp.setParty(new Individual());
		emp.setFirstName("Master");
		emp.setLastName("Lee");
		emp.setPartyCode("PC_005");
		emp.setRoleCode("RC_005");
		emp.setBranch(branch);
		emp.setDepartment(dept);
		return emp;
	}

	private IRelatedDepartment createDepartment(IPersistentBeanDescriptor deptDescriptor,
			IEducationOrganization branch, IRelatedDepartment parent) {
		IRelatedDepartment dept = new RelatedDepartment();
		dept.setParty(new Organization());
		dept.setPartyCode("PC_004");
		dept.setRoleCode("RC_004");
		dept.setPartyName("Gate Security");
		dept.setBranch(branch);
		dept.setParentDepartment(parent);
		return dept;
	}

	private IRelatedDepartment createDepartment(IPersistentBeanDescriptor deptDescriptor, IEducationOrganization branch) {
		IRelatedDepartment dept = new RelatedDepartment();
		dept.setParty(new Organization());
		dept.setPartyCode("PC_003");
		dept.setRoleCode("RC_003");
		dept.setPartyName("Security");
		dept.setBranch(branch);
		return dept;
	}

	private IEducationOrganization createEductationOrganization1(IPersistentBeanDescriptor branchDescriptor) {
		IEducationOrganization edu = new EducationOrganization();
		edu.setParty(new Organization());
		edu.setPartyCode("PC_001");
		edu.setPartyName("School 001");
		edu.setRoleCode("RC_001");
		return edu;
	}

	private IEducationOrganization createEductationOrganization2(IPersistentBeanDescriptor branchDescriptor,
			IEducationOrganization parent) {
		IEducationOrganization edu = new EducationOrganization();
		edu.setParty(new Organization());
		edu.setPartyCode("PC_002");
		edu.setPartyName("School 002");
		edu.setRoleCode("RC_002");
		edu.setParentBranch(parent);
		return edu;
	}
}
