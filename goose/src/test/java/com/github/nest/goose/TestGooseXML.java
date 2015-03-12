/**
 * 
 */
package com.github.nest.goose;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Test;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.ResourceDescriptorContextRepository;
import com.github.nest.arcteryx.meta.beans.internal.CachedBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.EmbeddablePersistentBeanDescriptor;
import com.github.nest.goose.internal.Code;
import com.github.nest.goose.internal.human.Gender;
import com.github.nest.goose.internal.location.Country;
import com.github.nest.goose.operate.OperateLog;

/**
 * @author brad.wu
 *
 */
public class TestGooseXML {
	@Test
	public void test() {
		{
			Context.createApplicationContextByClassPath("goose", "/goose.xml");
		}

		IPersistentBeanDescriptorContext context = ResourceDescriptorContextRepository.getContext("goose-context");
		assertNotNull(context);

		assertOperateLog(context);

		assertCountry(context);

		assertGender(context);
	}

	protected void assertCountry(IPersistentBeanDescriptorContext context) {
		CachedBeanDescriptor descriptor = context.get(Country.class);
		assertEquals(5, descriptor.getProperties().size());

		Collection<Country> list = descriptor.getCacheProvider().getBeans();
		assertEquals(241, list.size());

		Country country = descriptor.getFromCache(new Code("CHN"));
		assertEquals("CN", country.getAbbr2());
		assertEquals("CHN", country.getAbbr3());
		assertEquals("CHINA", country.getName());
		assertEquals("86", country.getInternationalDialingCode());
	}

	protected void assertGender(IPersistentBeanDescriptorContext context) {
		CachedBeanDescriptor descriptor = context.get(Gender.class);
		assertEquals(2, descriptor.getProperties().size());

		Collection<Gender> list = descriptor.getCacheProvider().getBeans();
		assertEquals(2, list.size());

		assertEquals(Gender.MALE, descriptor.getCacheProvider().getFromCache(new Code("M")));
		assertEquals(Gender.FEMALE, descriptor.getCacheProvider().getFromCache(new Code("F")));
	}

	protected void assertOperateLog(IPersistentBeanDescriptorContext context) {
		IPersistentBeanDescriptor descriptor = context.get(OperateLog.class);
		assertEquals(EmbeddablePersistentBeanDescriptor.class, descriptor.getClass());
		assertNotNull(descriptor);
		assertEquals(4, descriptor.getProperties().size());

		{
			IPersistentBeanPropertyDescriptor property = descriptor.getProperty("createUserId");
			IPrimitivePersistentColumn column = (IPrimitivePersistentColumn) property.getPersistentColumn();
			assertEquals("CREATE_USER_ID", column.getName());
			assertEquals(PrimitiveColumnType.LONG, column.getType());
			assertEquals(false, column.isPrimaryKey());
			assertNull(column.getPrimaryKeyGenerator());
			assertEquals(false, column.isVersion());
		}

		{
			IPersistentBeanPropertyDescriptor property = descriptor.getProperty("createTime");
			IPrimitivePersistentColumn column = (IPrimitivePersistentColumn) property.getPersistentColumn();
			assertEquals("CREATE_TIME", column.getName());
			assertEquals(PrimitiveColumnType.TIMESTAMP, column.getType());
			assertEquals(false, column.isPrimaryKey());
			assertNull(column.getPrimaryKeyGenerator());
			assertEquals(false, column.isVersion());
		}

		{
			IPersistentBeanPropertyDescriptor property = descriptor.getProperty("lastModifyUserId");
			IPrimitivePersistentColumn column = (IPrimitivePersistentColumn) property.getPersistentColumn();
			assertEquals("LAST_MODIFY_USER_ID", column.getName());
			assertEquals(PrimitiveColumnType.LONG, column.getType());
			assertEquals(false, column.isPrimaryKey());
			assertNull(column.getPrimaryKeyGenerator());
			assertEquals(false, column.isVersion());
		}

		{
			IPersistentBeanPropertyDescriptor property = descriptor.getProperty("lastModifyTime");
			IPrimitivePersistentColumn column = (IPrimitivePersistentColumn) property.getPersistentColumn();
			assertEquals("LAST_MODIFY_TIME", column.getName());
			assertEquals(PrimitiveColumnType.TIMESTAMP, column.getType());
			assertEquals(false, column.isPrimaryKey());
			assertNull(column.getPrimaryKeyGenerator());
			assertEquals(false, column.isVersion());
		}
	}
}
