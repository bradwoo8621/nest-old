/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NumberFormat;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.NumberFormatDef;

/**
 * number format convertor
 * 
 * @author brad.wu
 */
public class NumberFormatConvertor extends AbstractHibernateConstraintConvertor<NumberFormat> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(NumberFormat constraint) {
		return new NumberFormatDef().minIntegerDigits(constraint.getMinIntegerDigits())
				.maxIntegerDigits(constraint.getMaxIntegerDigits())
				.minFractionDigits(constraint.getMinFractionDigits())
				.maxFractionDigits(constraint.getMaxFractionDigits());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NumberFormat> getSupportedConstraintType() {
		return NumberFormat.class;
	}
}
