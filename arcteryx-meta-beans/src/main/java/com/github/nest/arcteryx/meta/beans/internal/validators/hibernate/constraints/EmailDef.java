/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * email def
 * 
 * @author brad.wu
 */
public class EmailDef extends ConstraintDef<EmailDef, Email> {
	public EmailDef() {
		super(Email.class);
	}

	public EmailDef allowPersonalName(boolean allowPersonalName) {
		this.addParameter("allowPersonalName", allowPersonalName);
		return this;
	}
}
