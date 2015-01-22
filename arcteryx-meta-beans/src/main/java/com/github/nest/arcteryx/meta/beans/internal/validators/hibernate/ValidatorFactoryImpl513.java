/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashSet;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.ParameterNameProvider;
import javax.validation.TraversableResolver;
import javax.validation.Validator;
import javax.validation.spi.ConfigurationState;

import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.HibernateValidatorContext;
import org.hibernate.validator.HibernateValidatorFactory;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.hibernate.validator.internal.metadata.provider.MetaDataProvider;
import org.hibernate.validator.internal.metadata.provider.XmlMetaDataProvider;
import org.hibernate.validator.internal.util.ExecutableHelper;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.hibernate.validator.spi.valuehandling.ValidatedValueUnwrapper;

/**
 * validation factory impl for 5.1.3.Final.<br>
 * copy from {@linkplain ValidatorFactoryImpl}
 * 
 * @author brad.wu
 */
public class ValidatorFactoryImpl513 implements HibernateValidatorFactory {
	private static final Log log = LoggerFactory.make();

	/**
	 * The default message interpolator for this factory.
	 */
	private final MessageInterpolator messageInterpolator;

	/**
	 * The default traversable resolver for this factory.
	 */
	private final TraversableResolver traversableResolver;

	/**
	 * The default parameter name provider for this factory.
	 */
	private final ParameterNameProvider parameterNameProvider;

	/**
	 * The default constraint validator factory for this factory.
	 */
	private final ConstraintValidatorManager constraintValidatorManager;

	/**
	 * Programmatic constraints passed via the Hibernate Validator specific API.
	 * Empty if there are no programmatic constraints
	 */
	private final Set<DefaultConstraintMapping> constraintMappings;

	/**
	 * Helper for dealing with built-in validators and determining custom
	 * constraint annotations.
	 */
	private final ConstraintHelper constraintHelper;

	/**
	 * Used for resolving type parameters. Thread-safe.
	 */
	private final TypeResolutionHelper typeResolutionHelper;

	/**
	 * Used for discovering overridden methods. Thread-safe.
	 */
	private final ExecutableHelper executableHelper;

	/**
	 * Hibernate Validator specific flag to abort validation on first constraint
	 * violation.
	 */
	private final boolean failFast;

	/**
	 * Metadata provider for XML configuration.
	 */
	private final XmlMetaDataProvider xmlMetaDataProvider;

	/**
	 * Prior to the introduction of {@code ParameterNameProvider} all the bean
	 * meta data was static and could be cached for all created
	 * {@code Validator}s. {@code ParameterNameProvider} makes parts of the meta
	 * data and Bean Validation element descriptors dynamic, since depending of
	 * the used provider different parameter names could be used. To still have
	 * the metadata static we create a {@code BeanMetaDataManager513} per parameter
	 * name provider. See also HV-659.
	 */
	private final Map<ParameterNameProvider, BeanMetaDataManager513> beanMetaDataManagerMap;

	/**
	 * Contains handlers to be applied to the validated value when validating
	 * elements.
	 */
	private final List<ValidatedValueUnwrapper<?>> validatedValueHandlers;

	public ValidatorFactoryImpl513(ConfigurationState configurationState) {
		this.messageInterpolator = configurationState.getMessageInterpolator();
		this.traversableResolver = configurationState.getTraversableResolver();
		this.parameterNameProvider = configurationState.getParameterNameProvider();
		this.beanMetaDataManagerMap = Collections
				.synchronizedMap(new IdentityHashMap<ParameterNameProvider, BeanMetaDataManager513>());
		this.constraintHelper = new ConstraintHelper();
		this.typeResolutionHelper = new TypeResolutionHelper();
		this.executableHelper = new ExecutableHelper(typeResolutionHelper);

		// HV-302; don't load XmlMappingParser if not necessary
		if (configurationState.getMappingStreams().isEmpty()) {
			this.xmlMetaDataProvider = null;
		} else {
			this.xmlMetaDataProvider = new XmlMetaDataProvider(constraintHelper, parameterNameProvider,
					configurationState.getMappingStreams());
		}

		Map<String, String> properties = configurationState.getProperties();

		boolean tmpFailFast = false;
		List<ValidatedValueUnwrapper<?>> tmpValidatedValueHandlers = newArrayList(5);
		Set<DefaultConstraintMapping> tmpConstraintMappings = newHashSet();

		if (configurationState instanceof ConfigurationImpl) {
			ConfigurationImpl hibernateSpecificConfig = (ConfigurationImpl) configurationState;

			if (hibernateSpecificConfig.getProgrammaticMappings().size() > 0) {
				tmpConstraintMappings.addAll(hibernateSpecificConfig.getProgrammaticMappings());
			}
			// check whether fail fast is programmatically enabled
			tmpFailFast = hibernateSpecificConfig.getFailFast();

			tmpValidatedValueHandlers.addAll(hibernateSpecificConfig.getValidatedValueHandlers());

		}
		this.constraintMappings = Collections.unmodifiableSet(tmpConstraintMappings);

		tmpFailFast = checkPropertiesForFailFast(properties, tmpFailFast);
		this.failFast = tmpFailFast;

		tmpValidatedValueHandlers.addAll(getPropertyConfiguredValidatedValueHandlers(properties));
		this.validatedValueHandlers = Collections.unmodifiableList(tmpValidatedValueHandlers);

		this.constraintValidatorManager = new ConstraintValidatorManager(
				configurationState.getConstraintValidatorFactory());
	}

	@Override
	public Validator getValidator() {
		return createValidator(constraintValidatorManager.getDefaultConstraintValidatorFactory(), messageInterpolator,
				traversableResolver, parameterNameProvider, failFast, validatedValueHandlers);
	}

	@Override
	public MessageInterpolator getMessageInterpolator() {
		return messageInterpolator;
	}

	@Override
	public TraversableResolver getTraversableResolver() {
		return traversableResolver;
	}

	@Override
	public ConstraintValidatorFactory getConstraintValidatorFactory() {
		return constraintValidatorManager.getDefaultConstraintValidatorFactory();
	}

	@Override
	public ParameterNameProvider getParameterNameProvider() {
		return parameterNameProvider;
	}

	public boolean isFailFast() {
		return failFast;
	}

	public List<ValidatedValueUnwrapper<?>> getValidatedValueHandlers() {
		return validatedValueHandlers;
	}

	@Override
	public <T> T unwrap(Class<T> type) {
		// allow unwrapping into public super types
		if (type.isAssignableFrom(HibernateValidatorFactory.class)) {
			return type.cast(this);
		}
		throw log.getTypeNotSupportedForUnwrappingException(type);
	}

	@Override
	public HibernateValidatorContext usingContext() {
		return new ValidatorContextImpl513(this);
	}

	@Override
	public void close() {
		constraintValidatorManager.clear();
		for (BeanMetaDataManager513 beanMetaDataManager : beanMetaDataManagerMap.values()) {
			beanMetaDataManager.clear();
		}
	}

	/**
	 * create validator
	 * 
	 * @param constraintValidatorFactory
	 * @param messageInterpolator
	 * @param traversableResolver
	 * @param parameterNameProvider
	 * @param failFast
	 * @param validatedValueHandlers
	 * @return
	 */
	protected Validator createValidator(ConstraintValidatorFactory constraintValidatorFactory,
			MessageInterpolator messageInterpolator, TraversableResolver traversableResolver,
			ParameterNameProvider parameterNameProvider, boolean failFast,
			List<ValidatedValueUnwrapper<?>> validatedValueHandlers) {
		BeanMetaDataManager513 beanMetaDataManager;
		if (!beanMetaDataManagerMap.containsKey(parameterNameProvider)) {
			beanMetaDataManager = new BeanMetaDataManager513(constraintHelper, executableHelper, parameterNameProvider,
					buildDataProviders(parameterNameProvider));
			beanMetaDataManagerMap.put(parameterNameProvider, beanMetaDataManager);
		} else {
			beanMetaDataManager = beanMetaDataManagerMap.get(parameterNameProvider);
		}

		return new ValidatorImpl(constraintValidatorFactory, messageInterpolator, traversableResolver,
				beanMetaDataManager, parameterNameProvider, typeResolutionHelper, validatedValueHandlers,
				constraintValidatorManager, failFast);
	}

	private List<MetaDataProvider> buildDataProviders(ParameterNameProvider parameterNameProvider) {
		List<MetaDataProvider> metaDataProviders = newArrayList();
		if (xmlMetaDataProvider != null) {
			metaDataProviders.add(xmlMetaDataProvider);
		}

		if (!constraintMappings.isEmpty()) {
			metaDataProviders.add(new ProgrammaticMetaDataProvider513(constraintHelper, parameterNameProvider,
					constraintMappings));
		}
		return metaDataProviders;
	}

	private boolean checkPropertiesForFailFast(Map<String, String> properties, boolean programmaticConfiguredFailFast) {
		boolean failFast = programmaticConfiguredFailFast;
		String failFastPropValue = properties.get(HibernateValidatorConfiguration.FAIL_FAST);
		if (failFastPropValue != null) {
			boolean tmpFailFast = Boolean.valueOf(failFastPropValue);
			if (programmaticConfiguredFailFast && !tmpFailFast) {
				throw log.getInconsistentFailFastConfigurationException();
			}
			failFast = tmpFailFast;
		}
		return failFast;
	}

	/**
	 * Returns a list with {@link ValidatedValueUnwrapper}s configured via the
	 * {@link HibernateValidatorConfiguration#VALIDATED_VALUE_HANDLERS}
	 * property.
	 *
	 * @param properties
	 *            the properties used to bootstrap the factory
	 *
	 * @return a list with property-configured {@link ValidatedValueUnwrapper}s;
	 *         May be empty but never {@code null}
	 */
	private List<ValidatedValueUnwrapper<?>> getPropertyConfiguredValidatedValueHandlers(Map<String, String> properties) {
		String propertyValue = properties.get(HibernateValidatorConfiguration.VALIDATED_VALUE_HANDLERS);

		if (propertyValue == null || propertyValue.isEmpty()) {
			return Collections.emptyList();
		}

		String[] handlerNames = propertyValue.split(",");
		List<ValidatedValueUnwrapper<?>> handlers = newArrayList(handlerNames.length);

		for (String handlerName : handlerNames) {
			@SuppressWarnings("unchecked")
			Class<? extends ValidatedValueUnwrapper<?>> handlerType = (Class<? extends ValidatedValueUnwrapper<?>>) run(LoadClass
					.action(handlerName, ValidatorFactoryImpl513.class));
			handlers.add(run(NewInstance.action(handlerType, "validated value handler class")));
		}

		return handlers;
	}

	/**
	 * Runs the given privileged action, using a privileged block if required.
	 * <p>
	 * <b>NOTE:</b> This must never be changed into a publicly available method
	 * to avoid execution of arbitrary privileged actions within HV's protection
	 * domain.
	 */
	private <T> T run(PrivilegedAction<T> action) {
		return System.getSecurityManager() != null ? AccessController.doPrivileged(action) : action.run();
	}
}
