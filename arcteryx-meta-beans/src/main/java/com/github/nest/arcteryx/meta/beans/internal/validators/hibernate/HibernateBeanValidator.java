/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.util.List;

import com.github.nest.arcteryx.meta.beans.IConstraintViolation;
import com.github.nest.arcteryx.meta.beans.internal.AbstractBeanValidator;

/**
 * hibernate bean validator
 * 
 * @author brad.wu
 */
public class HibernateBeanValidator extends AbstractBeanValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object)
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.String[])
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource, String... profiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.Class[])
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}
}
