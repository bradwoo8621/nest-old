/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.constraints.DateRangeConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.DateRangeDef;

/**
 * date range convertor
 * 
 * @author brad.wu
 */
public class DateRangeConvertor extends AbstractHibernateConstraintConvertor<DateRangeConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(DateRangeConstraint.class, DateRangeConstraint.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(DateRangeConstraint constraint) {
		return new DateRangeDef().max(constraint.getTo()).excludeMax(constraint.isExcludeTo())
				.min(constraint.getFrom()).excludeMin(constraint.isExcludeFrom()).format(constraint.getFormat())
				.tolerance(constraint.getTolerance());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<DateRangeConstraint> getSupportedConstraintType() {
		return DateRangeConstraint.class;
	}
}
