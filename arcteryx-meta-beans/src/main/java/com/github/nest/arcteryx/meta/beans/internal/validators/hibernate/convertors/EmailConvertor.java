/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.constraints.EmailConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.EmailDef;

/**
 * email convertor
 * 
 * @author brad.wu
 */
public class EmailConvertor extends AbstractHibernateConstraintConvertor<EmailConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(
				com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.Email.class,
				EmailConstraint.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(EmailConstraint constraint) {
		return new EmailDef().allowPersonalName(constraint.isAllowPersonalName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<EmailConstraint> getSupportedConstraintType() {
		return EmailConstraint.class;
	}
}
