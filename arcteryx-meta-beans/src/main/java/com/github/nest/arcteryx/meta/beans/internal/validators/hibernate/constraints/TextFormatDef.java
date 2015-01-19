/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * text format def
 * 
 * @author brad.wu
 */
public class TextFormatDef extends ConstraintDef<TextFormatDef, TextFormat> {
	public TextFormatDef() {
		super(TextFormat.class);
	}

	public TextFormatDef patterns(String... patterns) {
		this.addParameter("patterns", patterns);
		return this;
	}

	public TextFormatDef matchAll(boolean matchAll) {
		this.addParameter("matchAll", matchAll);
		return this;
	}
}
