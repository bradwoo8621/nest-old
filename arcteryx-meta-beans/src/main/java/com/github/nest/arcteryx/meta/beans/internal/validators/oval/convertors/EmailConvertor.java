/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.EmailCheck;

import com.github.nest.arcteryx.meta.beans.constraints.EmailConstraint;

/**
 * email convertor
 * 
 * @author brad.wu
 */
public class EmailConvertor extends AbstractOValPropertyCheckConvertor<EmailConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<EmailConstraint> getSupportedConstraintType() {
		return EmailConstraint.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(EmailConstraint constraint) {
		EmailCheck check = new EmailCheck();
		check.setAllowPersonalName(constraint.isAllowPersonalName());
		return check;
	}
}
