/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.constraints.TheNumberConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints.TheNumberCheck;

/**
 * number convertor
 * 
 * @author brad.wu
 */
public class TheNumberConvertor extends AbstractOValPropertyCheckConvertor<TheNumberConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<TheNumberConstraint> getSupportedConstraintType() {
		return TheNumberConstraint.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(TheNumberConstraint constraint) {
		TheNumberCheck check = new TheNumberCheck();
		check.setMax(constraint.getMax());
		check.setExcludeMax(constraint.isExcludeMax());
		check.setMin(constraint.getMin());
		check.setExcludeMin(constraint.isExcludeMin());
		return check;
	}
}
