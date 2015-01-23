/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.NotBlankCheck;

import com.github.nest.arcteryx.meta.beans.constraints.NotBlankConstraint;

/**
 * not blank convertor
 * 
 * @author brad.wu
 */
public class NotBlankConvertor extends AbstractOValPropertyCheckConvertor<NotBlankConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotBlankConstraint> getSupportedConstraintType() {
		return NotBlankConstraint.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(NotBlankConstraint constraint) {
		NotBlankCheck check = new NotBlankCheck();
		return check;
	}
}
