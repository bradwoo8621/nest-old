/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import javax.validation.ValidatorFactory;
import javax.validation.spi.ConfigurationState;

import org.hibernate.validator.HibernateValidator;

/**
 * hibernate validator 5.1.3.Final
 * 
 * @author brad.wu
 */
public class HibernateValidator513 extends HibernateValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.validator.HibernateValidator#buildValidatorFactory(javax.validation.spi.ConfigurationState)
	 */
	@Override
	public ValidatorFactory buildValidatorFactory(ConfigurationState configurationState) {
		return new ValidatorFactoryImpl513(configurationState);
	}
}
