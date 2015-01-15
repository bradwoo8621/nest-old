/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.DigitsCheck;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NumberFormat;

/**
 * number format convertor
 * 
 * @author brad.wu
 */
public class NumberFormatConvertor extends AbstractOValPropertyCheckConvertor<NumberFormat> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NumberFormat> getSupportedConstraintType() {
		return NumberFormat.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(NumberFormat constraint) {
		DigitsCheck check = new DigitsCheck();
		check.setMaxInteger(constraint.getMaxIntegerDigits());
		check.setMinInteger(constraint.getMinIntegerDigits());
		check.setMaxFraction(constraint.getMaxFractionDigits());
		check.setMinFraction(constraint.getMinFractionDigits());
		return check;
	}
}
