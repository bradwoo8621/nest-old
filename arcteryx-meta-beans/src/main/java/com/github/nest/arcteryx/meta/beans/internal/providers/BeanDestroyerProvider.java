/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.providers;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDestroyer;
import com.github.nest.arcteryx.meta.beans.internal.BeanDestroyer;

/**
 * default bean destroyer provider
 * 
 * @author brad.wu
 */
public class BeanDestroyerProvider implements IOperatorProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IBeanDestroyer createDefaultOperator(IResourceDescriptor descriptor) {
		return BeanDestroyer.DESTROYER;
	}
}
