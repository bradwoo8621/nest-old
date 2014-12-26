/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.defs;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.validation.hibernate.constraints.Length;

/**
 * definition of {@linkplain Length}
 * 
 * @author brad.wu
 */
public class LengthDef extends ConstraintDef<LengthDef, Length> {
	public LengthDef() {
		super(Length.class);
	}

	public LengthDef min(int min) {
		addParameter("min", min);
		return this;
	}

	public LengthDef max(int max) {
		addParameter("max", max);
		return this;
	}

	public LengthDef target(String target) {
		addParameter("target", target);
		return this;
	}

	public LengthDef when(String when) {
		addParameter("when", when);
		return this;
	}
}
