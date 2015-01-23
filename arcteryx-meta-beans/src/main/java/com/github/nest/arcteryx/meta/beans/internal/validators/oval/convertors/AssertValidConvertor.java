/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.AssertValidCheck;

import com.github.nest.arcteryx.meta.beans.constraints.AssertValidConstraint;

/**
 * assert valid convertor
 * 
 * @author brad.wu
 */
public class AssertValidConvertor extends AbstractOValPropertyCheckConvertor<AssertValidConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<AssertValidConstraint> getSupportedConstraintType() {
		return AssertValidConstraint.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(AssertValidConstraint constraint) {
		AssertValidCheck check = new AssertValidCheck();
		return check;
	}
}
