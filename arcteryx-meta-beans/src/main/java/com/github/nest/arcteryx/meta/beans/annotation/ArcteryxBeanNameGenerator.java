/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * arcteryx bean name generator
 * 
 * @author brad.wu
 */
public class ArcteryxBeanNameGenerator implements BeanNameGenerator {
	public static final String PREFIX = "arcteryx.meta.beans@";

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.annotation.AnnotationBeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition,
	 *      org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
//		if (definition instanceof AnnotatedBeanDefinition) {
//			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
//			if (StringUtils.hasText(beanName)) {
//				// Explicit bean name found.
//				return beanName;
//			}
//		}
		// Fallback: generate a unique default bean name.
		return buildDefaultBeanName(definition, registry);
	}

	/**
	 * Derive a bean name from one of the annotations on the class.
	 * 
	 * @param annotatedDef
	 *            the annotation-aware bean definition
	 * @return the bean name, or {@code null} if none is found
	 */
	protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		AnnotationMetadata amd = annotatedDef.getMetadata();
		Set<String> types = amd.getAnnotationTypes();
		String beanName = null;
		for (String type : types) {
			AnnotationAttributes attributes = AnnotationAttributes.fromMap(amd.getAnnotationAttributes(type, false));
			if (isStereotypeWithNameValue(type, amd.getMetaAnnotationTypes(type), attributes)) {
				Object value = attributes.get("name");
				if (value instanceof String) {
					String strVal = (String) value;
					if (StringUtils.hasLength(strVal)) {
						if (beanName != null && !strVal.equals(beanName)) {
							throw new IllegalStateException("Stereotype annotations suggest inconsistent "
									+ "component names: '" + beanName + "' versus '" + strVal + "'");
						}
						beanName = strVal;
					}
				}
			}
		}
		return beanName;
	}

	/**
	 * Check whether the given annotation is a stereotype that is allowed to
	 * suggest a component name through its annotation {@code name()}.
	 * 
	 * @param annotationType
	 *            the name of the annotation class to check
	 * @param metaAnnotationTypes
	 *            the names of meta-annotations on the given annotation
	 * @param attributes
	 *            the map of attributes for the given annotation
	 * @return whether the annotation qualifies as a stereotype with component
	 *         name
	 */
	protected boolean isStereotypeWithNameValue(String annotationType, Set<String> metaAnnotationTypes,
			Map<String, Object> attributes) {
		boolean isStereotype = annotationType.equals(ArcteryxBean.class.getName())
				|| (metaAnnotationTypes != null && metaAnnotationTypes.contains(ArcteryxBean.class.getName()));

		return (isStereotype && attributes != null && attributes.containsKey("name"));
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
