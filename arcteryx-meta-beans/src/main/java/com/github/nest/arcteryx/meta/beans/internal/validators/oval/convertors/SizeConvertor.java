/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.SizeCheck;

import com.github.nest.arcteryx.meta.beans.internal.constraints.Size;

/**
 * size convertor
 * 
 * @author brad.wu
 */
public class SizeConvertor extends AbstractOValPropertyCheckConvertor<Size> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<Size> getSupportedConstraintType() {
		return Size.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(Size constraint) {
		SizeCheck check = new SizeCheck();
		check.setMax(constraint.getMax());
		check.setMin(constraint.getMin());
		return check;
	}
}
