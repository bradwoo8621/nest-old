/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.TextFormat;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.TextFormatDef;

/**
 * text format convertor
 * 
 * @author brad.wu
 */
public class TextFormatConvertor extends AbstractHibernateConstraintConvertor<TextFormat> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(TextFormat constraint) {
		return new TextFormatDef().matchAll(constraint.isMatchAll()).patterns(constraint.getPatterns());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<TextFormat> getSupportedConstraintType() {
		return TextFormat.class;
	}
}
