/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.EmailCheck;

import com.github.nest.arcteryx.meta.beans.internal.constraints.Email;

/**
 * email convertor
 * 
 * @author brad.wu
 */
public class EmailConvertor extends AbstractOValPropertyCheckConvertor<Email> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<Email> getSupportedConstraintType() {
		return Email.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(Email constraint) {
		EmailCheck check = new EmailCheck();
		check.setAllowPersonalName(constraint.isAllowPersonalName());
		return check;
	}
}
