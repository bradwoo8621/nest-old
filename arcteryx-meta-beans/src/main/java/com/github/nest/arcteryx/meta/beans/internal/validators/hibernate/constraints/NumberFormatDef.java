/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * number format def
 * 
 * @author brad.wu
 */
public class NumberFormatDef extends ConstraintDef<NumberFormatDef, NumberFormat> {
	public NumberFormatDef() {
		super(NumberFormat.class);
	}

	public NumberFormatDef minIntegerDigits(int minIntegerDigits) {
		this.addParameter("minIntegerDigits", minIntegerDigits);
		return this;
	}

	public NumberFormatDef minFractionDigits(int minFractionDigits) {
		this.addParameter("minFractionDigits", minFractionDigits);
		return this;
	}

	public NumberFormatDef maxIntegerDigits(int maxIntegerDigits) {
		this.addParameter("maxIntegerDigits", maxIntegerDigits);
		return this;
	}

	public NumberFormatDef maxFractionDigits(int maxFractionDigits) {
		this.addParameter("maxFractionDigits", maxFractionDigits);
		return this;
	}
}
