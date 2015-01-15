/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.AssertValidCheck;

import com.github.nest.arcteryx.meta.beans.internal.constraints.AssertValid;

/**
 * assert valid convertor
 * 
 * @author brad.wu
 */
public class AssertValidConvertor extends AbstractOValPropertyCheckConvertor<AssertValid> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<AssertValid> getSupportedConstraintType() {
		return AssertValid.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(AssertValid constraint) {
		AssertValidCheck check = new AssertValidCheck();
		return check;
	}
}
