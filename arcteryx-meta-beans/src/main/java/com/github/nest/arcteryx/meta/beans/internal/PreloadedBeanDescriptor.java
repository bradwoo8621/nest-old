/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Collection;

import com.github.nest.arcteryx.meta.beans.IBeanPreloader;
import com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor;

/**
 * preloaded resource descriptor
 * 
 * @author brad.wu
 */
public class PreloadedBeanDescriptor extends BeanDescriptor implements IPreloadedBeanDescriptor {
	private static final long serialVersionUID = -7264079442274694957L;

	private PreloadedBeanDescriptorKernel kernel = null;

	public PreloadedBeanDescriptor() {
		this.kernel = createKernel();
	}

	/**
	 * create kernel
	 * 
	 * @return
	 */
	protected PreloadedBeanDescriptorKernel createKernel() {
		return new PreloadedBeanDescriptorKernel(this);
	}

	/**
	 * @return the kernel
	 */
	protected PreloadedBeanDescriptorKernel getKernel() {
		return kernel;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#getPreloader()
	 */
	@Override
	public IBeanPreloader getPreloader() {
		return this.getOperator(IBeanPreloader.CODE, IBeanPreloader.class);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#getPreloadedBeans()
	 */
	@Override
	public <T> Collection<T> getPreloadedBeans() {
		return getKernel().getPreloadedBeans();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#setPreloadedBeans(java.util.Collection)
	 */
	@Override
	public void setPreloadedBeans(Collection<?> beans) {
		getKernel().setPreloadedBeans(beans);
	}
}
