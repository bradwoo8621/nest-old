/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.TheNumber;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.TheNumberDef;

/**
 * number convertor
 * 
 * @author brad.wu
 */
public class TheNumberConvertor extends AbstractHibernateConstraintConvertor<TheNumber> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(TheNumber constraint) {
		return new TheNumberDef().min(constraint.getMin()).excludeMin(constraint.isExcludeMin())
				.max(constraint.getMax()).excludeMax(constraint.isExcludeMax());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<TheNumber> getSupportedConstraintType() {
		return TheNumber.class;
	}
}
