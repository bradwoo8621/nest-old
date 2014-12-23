/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.context.Context;

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
		BeanDescriptorContext context = Context.getContext("TestPreloadedBeans").getBean("beans.context",
				BeanDescriptorContext.class);
		ICachedBeanDescriptor descriptor = context.get(PreloadedBean.class, ICachedBeanDescriptor.class);
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
	}
}
