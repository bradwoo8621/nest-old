/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Collection;

import com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor;

/**
 * preloaded bean descriptor kernel.
 * 
 * @author brad.wu
 */
public class PreloadedBeanDescriptorKernel {
	private IPreloadedBeanDescriptor descriptor = null;
	private boolean preloaded = false;
	private Collection<?> preloadedBeans = null;

	public PreloadedBeanDescriptorKernel(IPreloadedBeanDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * get descriptor
	 * 
	 * @return
	 */
	protected IPreloadedBeanDescriptor getDescriptor() {
		return this.descriptor;
	}

	/**
	 * get preloaded beans
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Collection<T> getPreloadedBeans() {
		// for lazy loading
		if (!this.preloaded) {
			synchronized (this) {
				if (!this.preloaded) {
					this.preloadedBeans = getDescriptor().getPreloader().load();
					this.preloaded = true;
				}
			}
		}

		return (Collection<T>) this.preloadedBeans;
	}

	/**
	 * set preloaded beans
	 * 
	 * @param beans
	 */
	public void setPreloadedBeans(Collection<?> beans) {
		synchronized (this) {
			this.preloaded = true;
			this.preloadedBeans = beans;
		}
	}
}
