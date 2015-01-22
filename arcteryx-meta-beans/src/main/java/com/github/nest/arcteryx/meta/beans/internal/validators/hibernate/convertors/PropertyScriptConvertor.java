/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.hibernate.validator.cfg.ConstraintDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.beans.internal.constraints.PropertyScript;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;

/**
 * property script convertor
 * 
 * @author brad.wu
 */
public class PropertyScriptConvertor extends AbstractHibernateConstraintConvertor<PropertyScript> {
	static {
		Logger logger = LoggerFactory.getLogger(PropertyScriptConvertor.class);
		logger.error("Note: PropertyScript is not supported by Hibernate Validator 5.1.x, will throw BeanValidationException.");
	}

	/**
	 * PropertyScript constraint doesn't supported by hibernate validator yet,
	 * do nothing
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
	}

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
		throw new BeanValidationException("PropertyScript constraint doesn't supported by hibernate validator yet.");
	}
}
