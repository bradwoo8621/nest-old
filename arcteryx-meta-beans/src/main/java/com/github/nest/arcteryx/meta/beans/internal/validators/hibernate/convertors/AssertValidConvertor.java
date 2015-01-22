/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import javax.validation.Valid;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.AssertValid;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.AssertValidDef;

/**
 * assert valid convertor
 * 
 * @author brad.wu
 */
public class AssertValidConvertor extends AbstractHibernateConstraintConvertor<AssertValid> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(Valid.class, AssertValid.class.getSimpleName());
	}
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(AssertValid constraint) {
		return new AssertValidDef();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<AssertValid> getSupportedConstraintType() {
		return AssertValid.class;
	}
}
