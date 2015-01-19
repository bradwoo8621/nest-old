/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.DateRange;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.DateRangeDef;

/**
 * date range convertor
 * 
 * @author brad.wu
 */
public class DateRangeConvertor extends AbstractHibernateConstraintConvertor<DateRange> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(DateRange constraint) {
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
	public Class<DateRange> getSupportedConstraintType() {
		return DateRange.class;
	}
}
