/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanInitializer;
import com.github.nest.arcteryx.meta.beans.IStaticBeanDescriptor;

/**
 * static resource descriptor
 * 
 * @author brad.wu
 */
public class StaticBeanDescriptor extends BeanDescriptor implements IStaticBeanDescriptor {
	private static final long serialVersionUID = -7264079442274694957L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IStaticBeanDescriptor#getInitializer()
	 */
	@Override
	public IBeanInitializer getInitializer() {
		return this.getOperator(IBeanInitializer.CODE, IBeanInitializer.class);
	}
}
