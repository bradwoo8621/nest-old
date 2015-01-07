/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.providers;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanCreator;
import com.github.nest.arcteryx.meta.beans.internal.BeanCreator;

/**
 * bean creator provider
 * 
 * @author brad.wu
 */
public class BeanCreatorProvider implements IOperatorProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IBeanCreator createDefaultOperator(IResourceDescriptor descriptor) {
		IBeanCreator creator = new BeanCreator();
		creator.setResourceDescriptor(descriptor);
		return creator;
	}
}
