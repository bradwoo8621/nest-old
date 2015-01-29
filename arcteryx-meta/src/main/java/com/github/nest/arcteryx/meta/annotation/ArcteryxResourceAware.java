/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptorContext;

/**
 * arcteryx resource handler
 * 
 * @author brad.wu
 */
public class ArcteryxResourceAware implements ApplicationContextAware, InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext applicationContext;
	private IResourceDescriptorContext resourceContext = null;
	private Set<Class<? extends Annotation>> concernedAnnotations = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		IResourceDescriptorContext context = this.getResourceContext();
		for (Class<? extends Annotation> annotationClass : this.getConcernedAnnotations()) {
			String[] names = applicationContext.getBeanNamesForAnnotation(annotationClass);
			if (names != null) {
				for (String name : names) {
					Class<?> resourceClass = applicationContext.getType(name);
					if (logger.isInfoEnabled()) {
						logger.info("Auto scan bean [" + resourceClass.getName() + "]");
					}
					IResourceDescriptor descriptor = context.get(resourceClass);
					if (context.get(resourceClass) != null) {
						// already register by another annotation
						throw new AnnotationDefineException("Resource [" + name
								+ "] defines more than one bean annotation.");
					}

					ArcteryxResource resourceAnnotation = resourceClass.getAnnotation(annotationClass).annotationType()
							.getAnnotation(ArcteryxResource.class);
					descriptor = resourceAnnotation.generator().newInstance().createDescriptor(resourceClass);
					context.register(descriptor);
				}
			}
		}

		context.afterContextInitialized();
	}

	/**
	 * @return the resourceContext
	 */
	public IResourceDescriptorContext getResourceContext() {
		return resourceContext;
	}

	/**
	 * @param resourceContext
	 *            the resourceContext to set
	 */
	public void setResourceContext(IResourceDescriptorContext resourceContext) {
		this.resourceContext = resourceContext;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the concernedBeanAnnotations
	 */
	public Set<Class<? extends Annotation>> getConcernedAnnotations() {
		return concernedAnnotations;
	}

	/**
	 * @param concernedAnnotations
	 *            the concernedAnnotations to set
	 */
	public void setConcernedAnnotations(Set<Class<? extends Annotation>> concernedAnnotations) {
		this.concernedAnnotations = concernedAnnotations;
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}
}