/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.defs.SizeDef;

import com.github.nest.arcteryx.meta.beans.constraints.SizeConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;

/**
 * size convertor
 * 
 * @author brad.wu
 */
public class SizeConvertor extends AbstractHibernateConstraintConvertor<SizeConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(javax.validation.constraints.Size.class,
				SizeConstraint.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(SizeConstraint constraint) {
		return new SizeDef().min(constraint.getMin()).max(constraint.getMax());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<SizeConstraint> getSupportedConstraintType() {
		return SizeConstraint.class;
	}
}
