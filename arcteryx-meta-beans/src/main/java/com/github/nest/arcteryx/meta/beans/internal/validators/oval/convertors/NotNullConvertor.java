/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.NotNullCheck;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NotNull;

/**
 * Not Null convertor
 * 
 * @author brad.wu
 */
public class NotNullConvertor extends AbstractOValCheckConvertor<NotNull> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(NotNull constraint) {
		return new NotNullCheck();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotNull> getSupportedConstraintType() {
		return NotNull.class;
	}
}
