package com.github.nest.quelea.ut07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;
import com.github.nest.quelea.codes.City;
import com.github.nest.quelea.codes.Country;
import com.github.nest.quelea.codes.District;
import com.github.nest.quelea.codes.PartyRelationType;
import com.github.nest.quelea.codes.PartyType;
import com.github.nest.quelea.codes.PartyRelationType.PartyRelationTypeCodeItem;
import com.github.nest.quelea.codes.Province;

public class UT07Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createContextByClassPath("ut07",
				"/com/github/nest/quelea/ut07/Context.xml");
		ICodeTableRegistry registry = context.getBean("CodeTableRegistry", ICodeTableRegistry.class);

		Country country = registry.getCodeTable(Country.CODE_TABLE_NAME);
		assertTrue(country.contains("CHN"));
		assertTrue(country.contains("AFG"));
		assertTrue(country.contains("USA"));
		assertTrue(country.contains("SWE"));

		Province province = registry.getCodeTable(Province.CODE_TABLE_NAME);
		assertTrue(province.contains("AH"));
		assertTrue(province.contains("SH"));

		province = country.getProvince("USA");
		assertFalse(province.contains("AH"));

		province = country.getProvince("CHN");
		assertTrue(province.contains("AH"));
		assertEquals(34, province.getItems().size());

		City city = registry.getCodeTable(City.CODE_TABLE_NAME);
		assertTrue(city.contains("SH"));

		city = country.getCity("CHN", "SH");
		assertTrue(city.contains("SH"));

		District district = registry.getCodeTable(District.CODE_TABLE_NAME);
		assertTrue(district.contains("BS"));

		district = country.getDistrict("CHN", "SH", "SH");
		assertTrue(district.contains("JA"));

		PartyRelationType prt = registry.getCodeTable(PartyRelationType.CODE_TABLE_NAME);
		PartyRelationTypeCodeItem item = (PartyRelationTypeCodeItem) prt.getItem("1");
		assertEquals(PartyType.INDIVIDUAL, item.getSourcePartyTypeCode());
		assertEquals(PartyType.INDIVIDUAL, item.getTargetPartyTypeCode());
	}
}
