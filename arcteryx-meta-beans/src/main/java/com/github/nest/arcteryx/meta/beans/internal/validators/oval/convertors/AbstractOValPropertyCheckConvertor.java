/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;

/**
 * abstract OVal property check convertor
 * 
 * @author brad.wu
 */
public abstract class AbstractOValPropertyCheckConvertor<C extends IBeanPropertyConstraint> extends
		AbstractOValCheckConvertor<C> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#convert(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@Override
	public Check convert(C constraint) {
		Check check = super.convert(constraint);
		check.setTarget(constraint.getTarget());
		check.setAppliesTo(convertAppliesTo(constraint.getAppliesTo()));
		return check;
	}
}
