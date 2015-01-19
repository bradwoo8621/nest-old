/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * date range def
 * 
 * @author brad.wu
 */
public class DateRangeDef extends ConstraintDef<DateRangeDef, DateRange> {
	public DateRangeDef() {
		super(DateRange.class);
	}

	public DateRangeDef format(String format) {
		addParameter("format", format);
		return this;
	}

	public DateRangeDef max(String max) {
		addParameter("max", max);
		return this;
	}

	public DateRangeDef excludeMax(boolean excludeMax) {
		addParameter("excludeMax", excludeMax);
		return this;
	}

	public DateRangeDef min(String min) {
		addParameter("min", min);
		return this;
	}

	public DateRangeDef excludeMin(boolean excludeMin) {
		addParameter("excludeMin", excludeMin);
		return this;
	}

	public DateRangeDef tolerance(long tolerance) {
		addParameter("tolerance", tolerance);
		return this;
	}
}
