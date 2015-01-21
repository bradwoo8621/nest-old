/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidatorConfiguration;

import com.github.nest.arcteryx.meta.beans.IConstraintViolation;
import com.github.nest.arcteryx.meta.beans.internal.AbstractBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;

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
		Validator validator = getConfiguration().buildValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(resource);
		return convertViolations(violations);
	}

	/**
	 * profile must be name of class.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.String[])
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource, String... profiles) {
		if (profiles == null || profiles.length == 0) {
			return validate(resource);
		} else {
			List<Class<?>> list = new LinkedList<Class<?>>();
			for (String profile : profiles) {
				try {
					list.add(Class.forName(profile));
				} catch (ClassNotFoundException e) {
					throw new BeanValidationException("Profile [" + profile + "]cannot case to class.");
				}
			}
			if (list.size() == 0) {
				return validate(resource);
			}
			Class<?>[] groups = list.toArray(new Class<?>[list.size()]);
			return validate(resource, groups);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.Class[])
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource, Class<?>... groups) {
		Validator validator = getConfiguration().buildValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(resource, groups);
		return convertViolations(violations);
	}

	/**
	 * get configuration
	 * 
	 * @return
	 */
	protected HibernateValidatorConfiguration getConfiguration() {
		return this.getBeanDescriptor().getBeanDescriptorContext().getValidationConfiguration().getRealConfiguration();
	}

	/**
	 * convert violations
	 * 
	 * @param violations
	 * @return
	 */
	protected List<IConstraintViolation> convertViolations(Set<ConstraintViolation<Object>> violations) {
		if (violations == null || violations.size() == 0) {
			return Collections.emptyList();
		}

		List<IConstraintViolation> list = new LinkedList<IConstraintViolation>();
		for (ConstraintViolation<Object> violation : violations) {
			list.add(new HibernateConstraintViolation(violation));
		}
		return list;
	}
}
