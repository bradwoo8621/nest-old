/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * Code table name generator
 * 
 * @author brad.wu
 */
public class CodeTableNameGenerator implements BeanNameGenerator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.support.BeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition,
	 *      org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return "CodeTable#" + definition.getBeanClassName();
	}
}
