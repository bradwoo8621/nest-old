/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.internal.constraints.PropertyScript;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;

/**
 * property script convertor
 * 
 * @author brad.wu
 */
public class PropertyScriptConvertor extends AbstractHibernateConstraintConvertor<PropertyScript> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<PropertyScript> getSupportedConstraintType() {
		return PropertyScript.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(PropertyScript constraint) {
		throw new BeanValidationException("PropertyScript constraint not supported by hibernate validator yet.");
	}
}
