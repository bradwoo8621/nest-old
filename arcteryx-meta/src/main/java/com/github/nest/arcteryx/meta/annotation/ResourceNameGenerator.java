/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * arcteryx bean name generator
 * 
 * @author brad.wu
 */
public class ResourceNameGenerator implements BeanNameGenerator {
	public static final String PREFIX = "arcteryx.meta.beans@";

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.annotation.AnnotationBeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition,
	 *      org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return buildDefaultBeanName(definition, registry);
	}

	/**
	 * Derive a default bean name from the given bean definition.
	 * <p>
	 * The default implementation delegates to
	 * {@link #buildDefaultBeanName(BeanDefinition)}.
	 * 
	 * @param definition
	 *            the bean definition to build a bean name for
	 * @param registry
	 *            the registry that the given bean definition is being
	 *            registered with
	 * @return the default bean name (never {@code null})
	 */
	protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return buildDefaultBeanName(definition);
	}

	/**
	 * build default bean name
	 * 
	 * @param definition
	 * @return
	 */
	protected String buildDefaultBeanName(BeanDefinition definition) {
		return PREFIX + definition.getBeanClassName();
	}
}
