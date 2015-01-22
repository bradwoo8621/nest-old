/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.defs.LengthDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.Length;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;

/**
 * length convertor
 * 
 * @author brad.wu
 */
public class LengthConvertor extends AbstractHibernateConstraintConvertor<Length> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(org.hibernate.validator.constraints.Length.class,
				Length.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(Length constraint) {
		return new LengthDef().min(constraint.getMin()).max(constraint.getMax());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<Length> getSupportedConstraintType() {
		return Length.class;
	}
}
