/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.internal.constraints.Number;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints.NumberCheck;

/**
 * number convertor
 * 
 * @author brad.wu
 */
public class NumberConvertor extends AbstractOValPropertyCheckConvertor<Number> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<Number> getSupportedConstraintType() {
		return Number.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(Number constraint) {
		NumberCheck check = new NumberCheck();
		check.setMax(constraint.getMax());
		check.setExcludeMax(constraint.isExcludeMax());
		check.setMin(constraint.getMin());
		check.setExcludeMin(constraint.isExcludeMin());
		return check;
	}
}
