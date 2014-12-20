/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanPreloader;
import com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor;

/**
 * preloaded resource descriptor
 * 
 * @author brad.wu
 */
public class PreloadedBeanDescriptor extends BeanDescriptor implements IPreloadedBeanDescriptor {
	private static final long serialVersionUID = -7264079442274694957L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#getPreloader()
	 */
	@Override
	public IBeanPreloader getPreloader() {
		return this.getOperator(IBeanPreloader.CODE, IBeanPreloader.class);
	}
}
