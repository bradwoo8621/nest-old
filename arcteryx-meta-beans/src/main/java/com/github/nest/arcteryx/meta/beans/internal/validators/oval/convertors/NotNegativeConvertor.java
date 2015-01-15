/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.NotNegativeCheck;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NotNegative;

/**
 * Not negative convertor
 * 
 * @author brad.wu
 */
public class NotNegativeConvertor extends AbstractOValPropertyCheckConvertor<NotNegative> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(NotNegative constraint) {
		return new NotNegativeCheck();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotNegative> getSupportedConstraintType() {
		return NotNegative.class;
	}
}
