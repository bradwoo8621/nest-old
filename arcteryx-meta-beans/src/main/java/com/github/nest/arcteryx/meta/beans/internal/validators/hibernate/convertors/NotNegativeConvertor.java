/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NotNegative;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.NotNegativeDef;

/**
 * not negative convertor
 * 
 * @author brad.wu
 */
public class NotNegativeConvertor extends AbstractHibernateConstraintConvertor<NotNegative> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(NotNegative constraint) {
		return new NotNegativeDef();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotNegative> getSupportedConstraintType() {
		return NotNegative.class;
	}
}
