/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * number def
 * 
 * @author brad.wu
 */
public class TheNumberDef extends ConstraintDef<TheNumberDef, TheNumber> {
	public TheNumberDef() {
		super(TheNumber.class);
	}

	public TheNumberDef min(double min) {
		this.addParameter("min", min);
		return this;
	}

	public TheNumberDef excludeMin(boolean excludeMin) {
		this.addParameter("excludeMin", excludeMin);
		return this;
	}

	public TheNumberDef max(double max) {
		this.addParameter("max", max);
		return this;
	}

	public TheNumberDef excludeMax(boolean excludeMax) {
		this.addParameter("excludeMax", excludeMax);
		return this;
	}
}
