/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidatorContext;
import org.hibernate.validator.internal.engine.ValidatorContextImpl;
import org.hibernate.validator.spi.valuehandling.ValidatedValueUnwrapper;

/**
 * validator context implementation for 5.1.3.Final<br>
 * copy from {@linkplain ValidatorContextImpl}
 * 
 * @author brad.wu
 */
public class ValidatorContextImpl513 implements HibernateValidatorContext {
	private final ValidatorFactoryImpl513 validatorFactory;

	private MessageInterpolator messageInterpolator;
	private TraversableResolver traversableResolver;
	private ConstraintValidatorFactory constraintValidatorFactory;
	private ParameterNameProvider parameterNameProvider;

	private boolean failFast;
	private final List<ValidatedValueUnwrapper<?>> validatedValueHandlers;

	public ValidatorContextImpl513(ValidatorFactoryImpl513 validatorFactory) {
		this.validatorFactory = validatorFactory;
		this.messageInterpolator = validatorFactory.getMessageInterpolator();
		this.traversableResolver = validatorFactory.getTraversableResolver();
		this.constraintValidatorFactory = validatorFactory.getConstraintValidatorFactory();
		this.parameterNameProvider = validatorFactory.getParameterNameProvider();
		this.failFast = validatorFactory.isFailFast();
		this.validatedValueHandlers = new ArrayList<ValidatedValueUnwrapper<?>>(
				validatorFactory.getValidatedValueHandlers());
	}

	@Override
	public HibernateValidatorContext messageInterpolator(MessageInterpolator messageInterpolator) {
		if (messageInterpolator == null) {
			this.messageInterpolator = validatorFactory.getMessageInterpolator();
		} else {
			this.messageInterpolator = messageInterpolator;
		}
		return this;
	}

	@Override
	public HibernateValidatorContext traversableResolver(TraversableResolver traversableResolver) {
		if (traversableResolver == null) {
			this.traversableResolver = validatorFactory.getTraversableResolver();
		} else {
			this.traversableResolver = traversableResolver;
		}
		return this;
	}

	@Override
	public HibernateValidatorContext constraintValidatorFactory(ConstraintValidatorFactory factory) {
		if (factory == null) {
			this.constraintValidatorFactory = validatorFactory.getConstraintValidatorFactory();
		} else {
			this.constraintValidatorFactory = factory;
		}
		return this;
	}

	@Override
	public HibernateValidatorContext parameterNameProvider(ParameterNameProvider parameterNameProvider) {
		if (parameterNameProvider == null) {
			this.parameterNameProvider = validatorFactory.getParameterNameProvider();
		} else {
			this.parameterNameProvider = parameterNameProvider;
		}
		return this;
	}

	@Override
	public HibernateValidatorContext failFast(boolean failFast) {
		this.failFast = failFast;
		return this;
	}

	@Override
	public HibernateValidatorContext addValidationValueHandler(ValidatedValueUnwrapper<?> handler) {
		this.validatedValueHandlers.add(handler);
		return this;
	}

	@Override
	public Validator getValidator() {
		return validatorFactory.createValidator(constraintValidatorFactory, messageInterpolator, traversableResolver,
				parameterNameProvider, failFast, validatedValueHandlers);
	}
}
