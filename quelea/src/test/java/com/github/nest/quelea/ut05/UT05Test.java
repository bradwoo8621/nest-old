package com.github.nest.quelea.ut05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.quelea.codes.IndividualIdType;
import com.github.nest.quelea.codes.OrganizationIdType;
import com.github.nest.quelea.internal.model.Individual;
import com.github.nest.quelea.internal.model.Organization;
import com.github.nest.quelea.internal.model.Party;
import com.github.nest.quelea.internal.repository.IndividualRepository;
import com.github.nest.quelea.internal.repository.OrganizationRepository;
import com.github.nest.quelea.internal.repository.PartyRepository;
import com.github.nest.quelea.internal.support.IPartyNameStrategyFactory;

public class UT05Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		// Logger.getLogger("org.hibernate.type").setLevel(Level.TRACE);
		System.setProperty("spring.profiles.active", "test");
		ApplicationContext context = Context.createApplicationContextByClassPath("ut052",
				"/com/github/nest/quelea/ut05/Context.xml");
		IPartyNameStrategyFactory factory = context.getBean(IPartyNameStrategyFactory.class);

		Individual individual = new Individual();
		individual.setFirstName("John");
		individual.setLastName("Doe");
		individual.setIdNumber("I000001");
		individual.setIdTypeCode(IndividualIdType.ID_CARD);
		individual.setPartyName(factory.getPartyNameStrategy(individual).getPartyName(individual));

		Organization organization = new Organization();
		organization.setOrganizationName("Oracle");
		organization.setIdNumber("O000001");
		organization.setIdTypeCode(OrganizationIdType.ID_CARD);
		organization.setPartyName(factory.getPartyNameStrategy(organization).getPartyName(organization));

		PartyRepository rep = context.getBean(PartyRepository.class);
		assertNotNull(rep);

		PlatformTransactionManager tm = context.getBean(PlatformTransactionManager.class);
		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = tm.getTransaction(paramTransactionDefinition);
		try {
			rep.save(individual);
			rep.save(organization);
			tm.commit(status);
		} catch (Exception e) {
			tm.rollback(status);
			e.printStackTrace();
		}

		List<Party> list = rep.findByPartyName("John Doe");
		assertEquals(1, list.size());
		assertEquals(Individual.class, list.get(0).getClass());
		individual = (Individual) list.get(0);
		assertEquals("John", individual.getFirstName());
		assertEquals("Doe", individual.getLastName());
		assertEquals("I000001", individual.getIdNumber());
		assertEquals("John Doe", individual.getPartyName());

		list = rep.findByPartyName("Oracle");
		assertEquals(1, list.size());
		assertEquals(Organization.class, list.get(0).getClass());
		organization = (Organization) list.get(0);
		assertEquals("Oracle", organization.getOrganizationName());
		assertEquals("O000001", organization.getIdNumber());
		assertEquals("Oracle", organization.getPartyName());

		list = rep.findByPartyNameLike("e");
		assertEquals(2, list.size());
		list = rep.findByPartyNameLike("O");
		assertEquals(1, list.size());
		list = rep.findByPartyNameLike("o");
		assertEquals(1, list.size());

		list = rep.findByPartyNameStartingWith("Jo");
		assertEquals(1, list.size());
		Party party = rep.findByIdNumber("I000001");
		assertEquals(Individual.class, party.getClass());

		Page<Party> page = rep.findByPartyNameLike("e", new PageRequest(0, 10));
		assertEquals(1, page.getTotalPages());
		assertEquals(2, page.getTotalElements());

		IndividualRepository iRep = context.getBean(IndividualRepository.class);
		individual = iRep.findByIdNumberAndIdTypeCode("I000001", IndividualIdType.ID_CARD);
		assertNotNull(individual);

		OrganizationRepository oRep = context.getBean(OrganizationRepository.class);
		organization = oRep.findByIdNumberAndIdTypeCode("O000001", OrganizationIdType.ID_CARD);
		assertNotNull(organization);
	}
}
