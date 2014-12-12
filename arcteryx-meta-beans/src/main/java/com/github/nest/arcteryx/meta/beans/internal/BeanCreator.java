/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Map;

import com.github.nest.arcteryx.meta.AbstractResourceOperator;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.IBeanCreator;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;

/**
 * Bean creator
 * 
 * @author brad.wu
 */
public class BeanCreator extends AbstractResourceOperator implements IBeanCreator {
	/**
	 * if the parameter <code>resource</code> is null, call
	 * {@linkplain #create(IResourceDescriptor)}, otherwise call
	 * {@linkplain #fillWithDefaultValues(Object, IResourceDescriptor)}.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object,
	 *      com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public Object handle(Object resource, IResourceDescriptor descriptor) {
		assert descriptor != null : "Descriptor of resource cannot be null.";
		assert (descriptor instanceof IBeanDescriptor) : "Descriptor of resource must be an instance of "
				+ IBeanDescriptor.class.getName();

		if (resource == null) {
			return create((IBeanDescriptor) descriptor);
		} else {
			return fillWithDefaultValues(resource, (IBeanDescriptor) descriptor);
		}
	}

	/**
	 * bean must have a constructor withour parameter.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create(com.github.nest.arcteryx.meta.beans.IBeanDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(IBeanDescriptor descriptor) {
		Class<?> beanClass = descriptor.getBeanClass();
		Object object;
		try {
			object = beanClass.newInstance();
		} catch (InstantiationException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (IllegalAccessException e) {
			throw new ResourceException("Cannot construct object.", e);
		}
		return (T) object;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create(com.github.nest.arcteryx.meta.beans.IBeanDescriptor,
	 *      java.lang.Object[])
	 */
	@Override
	public <T> T create(IBeanDescriptor descriptor, Object... initialValues) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create(com.github.nest.arcteryx.meta.beans.IBeanDescriptor,
	 *      java.util.Map)
	 */
	@Override
	public <T> T create(IBeanDescriptor descriptor, Map<String, Object> initialValues) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#fillWithDefaultValues(java.lang.Object,
	 *      com.github.nest.arcteryx.meta.beans.IBeanDescriptor)
	 */
	@Override
	public <T> T fillWithDefaultValues(T resource, IBeanDescriptor descriptor) {
		// TODO Auto-generated method stub
		return null;
	}
}
