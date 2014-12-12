/**
 * 
 */
package com.github.nest.arcteryx.meta.internal;

import com.github.nest.arcteryx.meta.IStaticResourceDescriptor;
import com.github.nest.arcteryx.meta.IStaticResourceInitializer;

/**
 * static resource descriptor
 * 
 * @author brad.wu
 */
public class StaticResourceDescriptor extends ResourceDescriptor implements IStaticResourceDescriptor {
	private static final long serialVersionUID = -7264079442274694957L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IStaticResourceDescriptor#getInitializer()
	 */
	@Override
	public IStaticResourceInitializer getInitializer() {
		return this.getOperator(IStaticResourceInitializer.CODE, IStaticResourceInitializer.class);
	}
}
