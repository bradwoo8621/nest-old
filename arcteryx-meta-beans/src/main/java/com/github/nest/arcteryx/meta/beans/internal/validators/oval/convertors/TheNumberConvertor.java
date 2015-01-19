/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.internal.constraints.TheNumber;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints.TheNumberCheck;

/**
 * number convertor
 * 
 * @author brad.wu
 */
public class TheNumberConvertor extends AbstractOValPropertyCheckConvertor<TheNumber> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<TheNumber> getSupportedConstraintType() {
		return TheNumber.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(TheNumber constraint) {
		TheNumberCheck check = new TheNumberCheck();
		check.setMax(constraint.getMax());
		check.setExcludeMax(constraint.isExcludeMax());
		check.setMin(constraint.getMin());
		check.setExcludeMin(constraint.isExcludeMin());
		return check;
	}
}
