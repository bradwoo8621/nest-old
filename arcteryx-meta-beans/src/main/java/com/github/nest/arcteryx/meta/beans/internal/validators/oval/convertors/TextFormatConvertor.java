/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.internal.constraints.TextFormat;

/**
 * text format convertor
 * 
 * @author brad.wu
 */
public class TextFormatConvertor extends AbstractOValCheckConvertor<TextFormat> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<TextFormat> getSupportedConstraintType() {
		return TextFormat.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(TextFormat constraint) {
		TextFormatCheck check = new TextFormatCheck();
		check.setFormat(constraint.getPattern());
		return check;
	}
}
