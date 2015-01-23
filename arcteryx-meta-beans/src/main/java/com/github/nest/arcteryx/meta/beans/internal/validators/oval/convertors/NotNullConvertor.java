/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.NotNullCheck;

import com.github.nest.arcteryx.meta.beans.constraints.NotNullConstraint;

/**
 * Not Null convertor
 * 
 * @author brad.wu
 */
public class NotNullConvertor extends AbstractOValPropertyCheckConvertor<NotNullConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(NotNullConstraint constraint) {
		return new NotNullCheck();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotNullConstraint> getSupportedConstraintType() {
		return NotNullConstraint.class;
	}
}
