/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * not blank def
 * 
 * @author brad.wu
 */
public class NotBlankDef extends ConstraintDef<NotBlankDef, NotBlank> {
	public NotBlankDef() {
		super(NotBlank.class);
	}
}
