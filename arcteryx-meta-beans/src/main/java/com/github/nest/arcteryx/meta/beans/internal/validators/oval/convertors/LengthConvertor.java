/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.LengthCheck;

import com.github.nest.arcteryx.meta.beans.constraints.LengthConstraint;

/**
 * length convertor
 * 
 * @author brad.wu
 */
public class LengthConvertor extends AbstractOValPropertyCheckConvertor<LengthConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<LengthConstraint> getSupportedConstraintType() {
		return LengthConstraint.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(LengthConstraint constraint) {
		LengthCheck check = new LengthCheck();
		check.setMax(constraint.getMax());
		check.setMin(constraint.getMin());
		return check;
	}
}
