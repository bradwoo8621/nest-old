/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.constraints.NotEmptyConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.NotEmptyDef;

/**
 * not empty convertor
 * 
 * @author brad.wu
 */
public class NotEmptyConvertor extends AbstractHibernateConstraintConvertor<NotEmptyConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(
				com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.NotEmpty.class,
				NotEmptyConstraint.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(NotEmptyConstraint constraint) {
		return new NotEmptyDef();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotEmptyConstraint> getSupportedConstraintType() {
		return NotEmptyConstraint.class;
	}
}
