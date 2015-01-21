/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * not empty def
 * 
 * @author brad.wu
 */
public class NotEmptyDef extends ConstraintDef<NotEmptyDef, NotEmpty> {
	public NotEmptyDef() {
		super(NotEmpty.class);
	}
}
