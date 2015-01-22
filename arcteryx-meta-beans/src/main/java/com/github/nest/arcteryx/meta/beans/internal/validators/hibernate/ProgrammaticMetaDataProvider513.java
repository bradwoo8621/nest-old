/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

import java.util.List;
import java.util.Set;

import javax.validation.ParameterNameProvider;

import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.hibernate.validator.internal.metadata.provider.ProgrammaticMetaDataProvider;
import org.hibernate.validator.internal.metadata.raw.BeanConfiguration;

/**
 * programmatic meta data provider for Hibernate Validator 5.1.3.Final
 * 
 * @author brad.wu
 */
public class ProgrammaticMetaDataProvider513 extends ProgrammaticMetaDataProvider {
	public ProgrammaticMetaDataProvider513(ConstraintHelper constraintHelper,
			ParameterNameProvider parameterNameProvider, Set<DefaultConstraintMapping> constraintMappings) {
		super(constraintHelper, parameterNameProvider, constraintMappings);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.validator.internal.metadata.provider.MetaDataProviderKeyedByClassName#getBeanConfigurationForHierarchy(java.lang.Class)
	 */
	@Override
	public <T> List<BeanConfiguration<? super T>> getBeanConfigurationForHierarchy(Class<T> beanClass) {
		List<BeanConfiguration<? super T>> configurations = newArrayList();

		// only build bean configuration for itself
		BeanConfiguration<? super T> configuration = getBeanConfiguration(beanClass);
		if (configuration != null) {
			configurations.add(configuration);
		}

		return configurations;
	}
}
