/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.NotEmpty;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.NotEmptyDef;

/**
 * not empty convertor
 * 
 * @author brad.wu
 */
public class NotEmptyConvertor extends AbstractHibernateConstraintConvertor<NotEmpty> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(NotEmpty constraint) {
		return new NotEmptyDef();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<NotEmpty> getSupportedConstraintType() {
		return NotEmpty.class;
	}
}
