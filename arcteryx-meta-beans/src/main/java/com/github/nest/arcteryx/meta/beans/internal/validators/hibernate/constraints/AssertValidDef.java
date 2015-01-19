/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import javax.validation.Valid;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * assert valid def
 * 
 * @author brad.wu
 */
public class AssertValidDef extends ConstraintDef<AssertValidDef, Valid> {
	public AssertValidDef() {
		super(Valid.class);
	}
}
