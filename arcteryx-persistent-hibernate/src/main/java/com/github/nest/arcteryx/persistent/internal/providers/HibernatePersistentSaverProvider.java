/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.providers;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver;

/**
 * hibernate persistent saver provider
 * 
 * @author brad.wu
 */
public class HibernatePersistentSaverProvider implements IOperatorProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceOperator> T createDefaultOperator(IResourceDescriptor descriptor) {
		HibernatePersistentSaver saver = new HibernatePersistentSaver();
		saver.setResourceDescriptor(descriptor);
		return (T) saver;
	}
}
