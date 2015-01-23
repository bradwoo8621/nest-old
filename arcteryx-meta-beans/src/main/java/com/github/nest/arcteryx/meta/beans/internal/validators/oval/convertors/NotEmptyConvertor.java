/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.NotEmptyCheck;

import com.github.nest.arcteryx.meta.beans.constraints.NotEmptyConstraint;

/**
 * Not empty convertor
 * 
 * @author brad.wu
 */
public class NotEmptyConvertor extends AbstractOValPropertyCheckConvertor<NotEmptyConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(NotEmptyConstraint constraint) {
		return new NotEmptyCheck();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotEmptyConstraint> getSupportedConstraintType() {
		return NotEmptyConstraint.class;
	}
}
