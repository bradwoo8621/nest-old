/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;
import com.github.nest.arcteryx.meta.beans.internal.BeanCreator;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanPropertyDescriptor;

/**
 * @author brad.wu
 */
public class TestBeans {

	@Test
	public void test() {
		BeanDescriptor bd = new BeanDescriptor();
		bd.setBeanClass(Bean.class);

		BeanPropertyDescriptor pd = new BeanPropertyDescriptor();
		pd.setName("name");
		pd.setDefaultValue("default name");
		Collection<IPropertyDescriptor> pds = new ArrayList<IPropertyDescriptor>();
		pds.add(pd);
		bd.setProperties(pds);

		Collection<IResourceOperator> ros = new ArrayList<IResourceOperator>();
		ros.add(new BeanCreator());
		bd.setOperators(ros);

		IBeanCreator creator = bd.getCreator();
		Bean bean = creator.create();
		assertEquals("default name", bean.getName());

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("name", "test value");
		bean = creator.create(values);
		assertEquals("test value", bean.getName());
	}
}
