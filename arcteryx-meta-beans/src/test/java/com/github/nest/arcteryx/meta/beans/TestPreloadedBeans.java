/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptorContext;

/**
 * @author brad.wu
 */
public class TestPreloadedBeans {
	@BeforeClass
	public static void initialize() {
		Context.createApplicationContextByClassPath("Beans.PreloadedBean", "/PreloadedBeans.xml");
		Context.createApplicationContextByClassPath("Cache", "/cache.xml");
		Context.createApplicationContextByClassPath("TestPreloadedBeans", "/TestBeans.xml");
	}

	@Test
	public void testPreload() {
		IBeanDescriptorContext context = Context.getContext("TestPreloadedBeans").getBean("beans.context",
				BeanDescriptorContext.class);
		ICachedBeanDescriptor descriptor = context.get(PreloadedBean.class);
		assertNotNull(descriptor.getCreator());
		assertNotNull(descriptor.getDestoryer());

		IBeanFinder finder = descriptor.getFinder();

		BeanID id = new BeanID();
		id.setId("001");
		PreloadedBean bean = finder.find(id);
		assertEquals("Test Bean 001", bean.getName());

		id = new BeanID();
		id.setId("002");
		bean = finder.find(id);
		assertEquals("Test Bean 002", bean.getName());

		id = new BeanID();
		id.setId("003");
		bean = descriptor.getFromCache(id);
		assertEquals("Test Bean 003", bean.getName());

		IBeanSorter sorter = descriptor.getSorter("sorter");
		List<PreloadedBean> beans = sorter.sort();
		assertEquals(3, beans.size());
		assertEquals("Test Bean 003", beans.get(0).getName());
		assertEquals("Test Bean 002", beans.get(1).getName());
		assertEquals("Test Bean 001", beans.get(2).getName());

		beans = descriptor.getSortedBeans("sorter");
		assertEquals(3, beans.size());
		assertEquals("Test Bean 003", beans.get(0).getName());
		assertEquals("Test Bean 002", beans.get(1).getName());
		assertEquals("Test Bean 001", beans.get(2).getName());
	}
}
