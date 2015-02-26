/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.providers;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentLoader;

/**
 * @author brad.wu
 *
 */
public class HibernatePersistentLoaderProvider implements IOperatorProvider {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceOperator> T createDefaultOperator(IResourceDescriptor descriptor) {
		HibernatePersistentLoader loader = new HibernatePersistentLoader();
		loader.setResourceDescriptor(descriptor);
		return (T) loader;
	}
}
