/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.defs.SizeDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.Size;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;

/**
 * size convertor
 * 
 * @author brad.wu
 */
public class SizeConvertor extends AbstractHibernateConstraintConvertor<Size> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(javax.validation.constraints.Size.class,
				Size.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(Size constraint) {
		return new SizeDef().min(constraint.getMin()).max(constraint.getMax());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<Size> getSupportedConstraintType() {
		return Size.class;
	}
}
