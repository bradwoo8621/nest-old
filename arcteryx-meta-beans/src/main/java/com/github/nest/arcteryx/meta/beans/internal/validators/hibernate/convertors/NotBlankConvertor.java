/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.defs.NotBlankDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NotBlank;

/**
 * not blank convertor
 * 
 * @author brad.wu
 */
public class NotBlankConvertor extends AbstractHibernateConstraintConvertor<NotBlank> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(NotBlank constraint) {
		return new NotBlankDef();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotBlank> getSupportedConstraintType() {
		return NotBlank.class;
	}
}