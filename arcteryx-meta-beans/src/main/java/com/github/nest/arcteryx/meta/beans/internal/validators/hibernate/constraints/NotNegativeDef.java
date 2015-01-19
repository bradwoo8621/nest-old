/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * not negative def
 * 
 * @author brad.wu
 */
public class NotNegativeDef extends ConstraintDef<NotNegativeDef, NotNegative> {
	public NotNegativeDef() {
		super(NotNegative.class);
	}
}
