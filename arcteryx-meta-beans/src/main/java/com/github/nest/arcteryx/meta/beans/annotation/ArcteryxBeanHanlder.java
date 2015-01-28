/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.annotation.AnnotationDefineException;
import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.IConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.CachedBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.SpringCachedBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.utils.ReflectionUtils;

/**
 * arcteryx bean handler
 * 
 * @author brad.wu
 */
public class ArcteryxBeanHanlder implements ApplicationContextAware, InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext applicationContext;
	private IBeanDescriptorContext beanContext = null;

	@SuppressWarnings("rawtypes")
	private Map<Class<? extends Annotation>, IBeanDescriptorGenerator> beanDescriptorGenerators = new HashMap<Class<? extends Annotation>, IBeanDescriptorGenerator>();
	private Map<Class<? extends Annotation>, IBeanPropertyDescriptorGenerator> propertyDescriptorGenerators = new HashMap<Class<? extends Annotation>, IBeanPropertyDescriptorGenerator>();
	@SuppressWarnings("rawtypes")
	private Map<Class<? extends IConstraintReorganizer>, IConstraintReorganizerGenerator> reorganizerGenerators = new HashMap<Class<? extends IConstraintReorganizer>, IConstraintReorganizerGenerator>();

	public ArcteryxBeanHanlder() {
		this.setBeanDescriptorGenerators(initializeBeanDescriptorGenerators());
		this.setPropertyDescriptorGenerators(initializePropertyDescriptorGenerators());
		this.setConstraintReorganizerGenerators(this.initializeConstraintReorganizerGenerators());
	}

	/**
	 * initialize bean property descriptor generators
	 * 
	 * @return
	 */
	protected List<IBeanPropertyDescriptorGenerator> initializePropertyDescriptorGenerators() {
		return new LinkedList<IBeanPropertyDescriptorGenerator>();
	}

	/**
	 * initialize bean descriptor generators
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected List<IBeanDescriptorGenerator> initializeBeanDescriptorGenerators() {
		List<IBeanDescriptorGenerator> generators = new LinkedList<IBeanDescriptorGenerator>();
		generators.add(new BeanDescriptorGenerator());
		generators.add(new CachedBeanDescriptorGenerator());
		generators.add(new SpringCachedBeanDescriptorGenerator());
		return generators;
	}

	/**
	 * initialize constraint reorganizer generators
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected List<IConstraintReorganizerGenerator> initializeConstraintReorganizerGenerators() {
		List<IConstraintReorganizerGenerator> generators = new LinkedList<IConstraintReorganizerGenerator>();
		generators.add(new BeanConstraintReorganizerGenerator());
		generators.add(new BeanPropetyConstraintReorganizerGenerator());
		return generators;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		IBeanDescriptorContext context = this.getBeanContext();
		for (Class<? extends Annotation> annotationClass : this.beanDescriptorGenerators.keySet()) {
			String[] names = applicationContext.getBeanNamesForAnnotation(annotationClass);
			if (names != null) {
				for (String name : names) {
					Class<?> beanClass = applicationContext.getType(name);
					if (logger.isInfoEnabled()) {
						logger.info("Auto scan bean [" + beanClass.getName() + "]");
					}
					IBeanDescriptor descriptor = context.get(beanClass);
					if (context.get(beanClass) != null) {
						// already register by another annotation
						throw new AnnotationDefineException("Bean [" + name
								+ "] defines more than one bean annotation.");
					}
					descriptor = getBeanDescriptorGenerator(annotationClass).createDescriptor(beanClass);
					context.register(descriptor);
				}
			}
		}

		context.afterContextInitialized();
	}

	/**
	 * get bean descriptor generator
	 * 
	 * @param annotationClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected IBeanDescriptorGenerator getBeanDescriptorGenerator(Class<? extends Annotation> annotationClass) {
		return beanDescriptorGenerators.get(annotationClass);
	}

	/**
	 * @return the beanDescriptorGenerators
	 */
	@SuppressWarnings("rawtypes")
	protected Map<Class<? extends Annotation>, IBeanDescriptorGenerator> getBeanDescriptorGenerators() {
		return beanDescriptorGenerators;
	}

	/**
	 * get property descriptor generator
	 * 
	 * @param annotationClass
	 * @return
	 */
	protected IBeanPropertyDescriptorGenerator getPropertyDescriptorGenerator(
			Class<? extends Annotation> annotationClass) {
		return propertyDescriptorGenerators.get(annotationClass);
	}

	/**
	 * @return the propertyDescriptorGenerators
	 */
	protected Map<Class<? extends Annotation>, IBeanPropertyDescriptorGenerator> getPropertyDescriptorGenerators() {
		return propertyDescriptorGenerators;
	}

	/**
	 * get constraint reorganizer generator
	 * 
	 * @param reorganizerClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected IConstraintReorganizerGenerator getConstraintReorganizerGenerator(
			Class<? extends IConstraintReorganizer> reorganizerClass) {
		return this.reorganizerGenerators.get(reorganizerClass);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the beanContext
	 */
	protected IBeanDescriptorContext getBeanContext() {
		return beanContext;
	}

	/**
	 * @param beanContext
	 *            the beanContext to set
	 */
	public void setBeanContext(IBeanDescriptorContext beanContext) {
		this.beanContext = beanContext;
	}

	/**
	 * set bean descriptor generators
	 * 
	 * @param generators
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setBeanDescriptorGenerators(List<IBeanDescriptorGenerator> generators) {
		if (generators != null) {
			for (IBeanDescriptorGenerator generator : generators) {
				this.beanDescriptorGenerators.put(generator.getSupportedClass(), generator);
				generator.setHandler(this);
			}
		}
	}

	/**
	 * set property descriptor generators
	 * 
	 * @param generators
	 */
	public void setPropertyDescriptorGenerators(List<IBeanPropertyDescriptorGenerator> generators) {
		if (generators != null) {
			for (IBeanPropertyDescriptorGenerator generator : generators) {
				this.propertyDescriptorGenerators.put(generator.getSupportedClass(), generator);
				generator.setHandler(this);
			}
		}
	}

	/**
	 * set constraint reogranizer generators
	 * 
	 * @param generators
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setConstraintReorganizerGenerators(List<IConstraintReorganizerGenerator> generators) {
		if (generators != null) {
			for (IConstraintReorganizerGenerator generator : generators) {
				this.reorganizerGenerators.put(generator.getSupportedClass(), generator);
			}
		}
	}

	/**
	 * create constraint
	 * 
	 * @param annotation
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static IConstraint createConstraint(Annotation annotation) throws Exception {
		final Constraint constraintAnnotation = annotation.annotationType().getAnnotation(Constraint.class);
		IConstraint constraint = constraintAnnotation.constraintClass().newInstance();
		constraint.configure(annotation);
		return constraint;
	}

	/**
	 * generate annotation to constraints
	 * 
	 * @param annotations
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected static List<IConstraint> generateConstraints(Annotation[] annotations) throws Exception {
		List<IConstraint> constraints = new LinkedList<IConstraint>();
		if (annotations != null && annotations.length != 0) {
			for (Annotation annotation : annotations) {
				if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
					constraints.add(createConstraint(annotation));
				} else if (annotation.annotationType().isAnnotationPresent(Constraints.class)) {
					final Method getValue = annotation.annotationType().getDeclaredMethod("values", (Class<?>[]) null);
					final Object[] children = (Object[]) getValue.invoke(annotation, (Object[]) null);
					for (Object child : children) {
						constraints.add(createConstraint((Annotation) child));
					}
				}
			}
		}
		return constraints;
	}

	/**
	 * 
	 * @param beanClass
	 * @return
	 */
	protected static Annotation getConstraintReorganizer(Class<?> beanClass) {
		Annotation reorganizer = null;
		Annotation[] annotations = beanClass.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().isAnnotationPresent(ConstraintReorganizer.class)) {
				if (reorganizer == null) {
					reorganizer = annotation;
				} else {
					throw new AnnotationDefineException("Only one constraint reorganizer annotation can be defined.");
				}
			}
		}
		return reorganizer;
	}

	/**
	 * get constraint reorganizer
	 * 
	 * @param field
	 * @param getter
	 * @param setter
	 * @return
	 */
	protected static Annotation getConstraintReorganizer(Field field, Method getter, Method setter) {
		Annotation[] a1 = field == null ? null : field.getAnnotations();
		Annotation[] a2 = getter == null ? null : getter.getAnnotations();
		Annotation[] a3 = setter == null ? null : setter.getAnnotations();
		List<Annotation> all = new LinkedList<Annotation>();
		all.addAll(filterAnnotations(a1, ConstraintReorganizer.class));
		all.addAll(filterAnnotations(a2, ConstraintReorganizer.class));
		all.addAll(filterAnnotations(a3, ConstraintReorganizer.class));
		if (all.size() == 0) {
			return null;
		} else if (all.size() == 1) {
			return all.get(0);
		} else {
			throw new AnnotationDefineException("Constraint reogranizer @ property [" + field.getName() + "@"
					+ field.getType().getName() + "] defines repeated, only one is allowed.");
		}
	}

	/**
	 * filter annotations by given class
	 * 
	 * @param annotations
	 * @param annotationClass
	 * @return
	 */
	protected static Collection<Annotation> filterAnnotations(Annotation[] annotations,
			Class<? extends Annotation> annotationClass) {
		List<Annotation> all = new LinkedList<Annotation>();
		if (annotations != null) {
			for (Annotation annotation : annotations) {
				if (annotation.annotationType().isAnnotationPresent(annotationClass)) {
					all.add(annotation);
				}
			}
		}
		return all;
	}

	/**
	 * bean descriptor generator
	 * 
	 * @author brad.wu
	 *
	 */
	public static interface IBeanDescriptorGenerator<A extends Annotation> {
		/**
		 * set handler
		 * 
		 * @param handler
		 */
		void setHandler(ArcteryxBeanHanlder handler);

		/**
		 * get handler
		 * 
		 * @return
		 */
		ArcteryxBeanHanlder getHandler();

		/**
		 * get supported class
		 * 
		 * @return
		 */
		Class<A> getSupportedClass();

		/**
		 * create descriptor
		 * 
		 * @param beanClass
		 * 
		 * @return
		 * @throws Exception
		 */
		IBeanDescriptor createDescriptor(Class<?> beanClass) throws Exception;
	}

	/**
	 * bean property descriptor generator
	 * 
	 * @author brad.wu
	 */
	public static interface IBeanPropertyDescriptorGenerator {
		/**
		 * set handler
		 * 
		 * @param handler
		 */
		void setHandler(ArcteryxBeanHanlder handler);

		/**
		 * get handler
		 * 
		 * @return
		 */
		ArcteryxBeanHanlder getHandler();

		/**
		 * get supported class
		 * 
		 * @return
		 */
		Class<? extends Annotation> getSupportedClass();

		/**
		 * create descriptor
		 * 
		 * @param field
		 * 
		 * @return
		 * @throws Exception
		 */
		IBeanPropertyDescriptor createDescriptor(Field field) throws Exception;

		/**
		 * create descriptor
		 * 
		 * @param method
		 * @return
		 * @throws Exception
		 */
		IBeanPropertyDescriptor createDescriptor(Method method) throws Exception;

		/**
		 * read advanced definition
		 * 
		 * @param property
		 */
		void readAdvanced(IBeanPropertyDescriptor property) throws Exception;
	}

	public static abstract class AbstractBeanDescriptorGenerator<A extends Annotation, D extends IBeanDescriptor>
			implements IBeanDescriptorGenerator<A> {
		private ArcteryxBeanHanlder handler = null;

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#setHandler(com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder)
		 */
		@Override
		public void setHandler(ArcteryxBeanHanlder handler) {
			this.handler = handler;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#getHandler()
		 */
		@Override
		public ArcteryxBeanHanlder getHandler() {
			return this.handler;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#createDescriptor(java.lang.Class)
		 */
		@Override
		public D createDescriptor(Class<?> beanClass) throws Exception {
			return createDescriptor(getAnnotation(beanClass), beanClass);
		}

		/**
		 * get annotation
		 * 
		 * @param beanClass
		 * @return
		 */
		protected abstract A getAnnotation(Class<?> beanClass);

		/**
		 * create descriptor
		 * 
		 * @param annotation
		 * @param beanClass
		 * @return
		 */
		protected abstract D createDescriptor(A annotation, Class<?> beanClass);
	}

	/**
	 * bean descriptor generator
	 * 
	 * @author brad.wu
	 */
	public static class BeanDescriptorGenerator<A extends Annotation, D extends BeanDescriptor> extends
			AbstractBeanDescriptorGenerator<A, D> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#getSupportedClass()
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Class<A> getSupportedClass() {
			return (Class<A>) ArcteryxBean.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#createDescriptor(java.lang.Class,
		 *      com.github.nest.arcteryx.meta.beans.IBeanDescriptor)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public D createDescriptor(Class<?> beanClass) throws Exception {
			BeanDescriptor descriptor = super.createDescriptor(beanClass);
			// constraint reorganizer
			Annotation reorganizer = getConstraintReorganizer(beanClass);
			if (reorganizer != null) {
				descriptor.setConstraintReorganizer((IBeanConstraintReorganizer) getHandler()
						.getConstraintReorganizerGenerator(
								reorganizer.annotationType().getAnnotation(ConstraintReorganizer.class)
										.reorganizerClass()).createReorganizer(reorganizer));
			}
			// constraints
			readTypeConstraints(beanClass, descriptor);
			// properties
			readProperties(beanClass, descriptor);

			return (D) descriptor;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.AbstractBeanDescriptorGenerator#getAnnotation(java.lang.Class)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected A getAnnotation(Class<?> beanClass) {
			return (A) beanClass.getAnnotation(ArcteryxBean.class);
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.AbstractBeanDescriptorGenerator#createDescriptor(java.lang.annotation.Annotation,
		 *      java.lang.Class)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected D createDescriptor(A annotation, Class<?> beanClass) {
			ArcteryxBean bean = (ArcteryxBean) annotation;
			BeanDescriptor descriptor = new BeanDescriptor();
			descriptor.setName(bean.name());
			descriptor.setBeanClass(beanClass);
			descriptor.setDescription(bean.description());
			return (D) descriptor;
		}

		/**
		 * read properties
		 * 
		 * @param beanClass
		 * @param descriptor
		 * @throws Exception
		 */
		protected void readProperties(Class<?> beanClass, BeanDescriptor descriptor) throws Exception {
			List<IBeanPropertyDescriptor> properties = new LinkedList<IBeanPropertyDescriptor>();
			Map<String, IBeanPropertyDescriptorGenerator> generatorMap = new HashMap<String, IBeanPropertyDescriptorGenerator>();
			Field[] fields = beanClass.getDeclaredFields();
			for (Field field : fields) {
				IBeanPropertyDescriptorGenerator generator = null;
				// search secondary annotation
				for (Class<? extends Annotation> annotationClass : this.getHandler().getPropertyDescriptorGenerators()
						.keySet()) {
					Annotation annotation = field.getAnnotation(annotationClass);
					if (annotation != null) {
						if (generator != null) {
							throw new AnnotationDefineException("Property [" + field.getName() + "@"
									+ beanClass.getName() + "] already defined more than one property annotation.");
						}
						generator = this.getHandler().getPropertyDescriptorGenerator(annotationClass);
					}
				}
				if (generator != null || field.getAnnotation(ArcteryxBeanProperty.class) != null) {
					generator = new BeanPropertyDescriptorGenerator(generator);
					generator.setHandler(this.getHandler());
					IBeanPropertyDescriptor property = generator.createDescriptor(field);
					properties.add(property);
					generatorMap.put(property.getName(), generator);
				}
			}
			Method[] methods = beanClass.getDeclaredMethods();
			for (Method method : methods) {
				IBeanPropertyDescriptorGenerator generator = null;
				// search secondary annotation
				for (Class<? extends Annotation> annotationClass : this.getHandler().getPropertyDescriptorGenerators()
						.keySet()) {
					Annotation annotation = method.getAnnotation(annotationClass);
					if (annotation != null) {
						if (generator != null) {
							throw new AnnotationDefineException("Property [" + ReflectionUtils.getPropertyName(method)
									+ "@" + beanClass.getName()
									+ "] already defined more than one property annotation.");
						}
						generator = this.getHandler().getPropertyDescriptorGenerator(annotationClass);
					}
				}
				if (generator != null || method.getAnnotation(ArcteryxBeanProperty.class) != null) {
					generator = new BeanPropertyDescriptorGenerator(generator);
					IBeanPropertyDescriptor property = generator.createDescriptor(method);
					generator.setHandler(this.getHandler());
					if (generatorMap.containsKey(property.getName())) {
						throw new AnnotationDefineException("Property [" + property.getName() + "@"
								+ beanClass.getName() + "] already defined in other field/method, only one is allowed.");
					}
					properties.add(property);
					generatorMap.put(property.getName(), generator);
				}
			}
			List<IPropertyDescriptor> list = new ArrayList<IPropertyDescriptor>(properties.size());
			list.addAll(properties);
			descriptor.setProperties(list);

			// loop the created properties and find their field/getter/setter,
			// read the constraints, reorganizer and default value
			for (IBeanPropertyDescriptor property : properties) {
				generatorMap.get(property.getName()).readAdvanced(property);
			}
		}

		/**
		 * read type constraints
		 * 
		 * @param beanClass
		 * @param descriptor
		 * @throws Exception
		 */
		@SuppressWarnings("rawtypes")
		protected void readTypeConstraints(Class<?> beanClass, BeanDescriptor descriptor) throws Exception {
			List<IConstraint> constraints = generateConstraints(beanClass.getAnnotations());
			if (constraints != null && constraints.size() != 0) {
				descriptor.setConstraints(this.convertToBeanConstraints(constraints));
			}
		}

		/**
		 * convert to bean constraint list
		 * 
		 * @param constraints
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		protected List<IBeanConstraint> convertToBeanConstraints(List<IConstraint> constraints) {
			List<IBeanConstraint> list = new ArrayList<IBeanConstraint>(constraints.size());
			for (IConstraint constraint : constraints) {
				list.add((IBeanConstraint) constraint);
			}
			return list;
		}
	}

	public static class CachedBeanDescriptorGenerator<A extends Annotation, D extends CachedBeanDescriptor> extends
			BeanDescriptorGenerator<A, D> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.BeanDescriptorGenerator#getSupportedClass()
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Class<A> getSupportedClass() {
			return (Class<A>) ArcteryxCachedBean.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.BeanDescriptorGenerator#getAnnotation(java.lang.Class)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected A getAnnotation(Class<?> beanClass) {
			return (A) beanClass.getAnnotation(ArcteryxCachedBean.class);
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.BeanDescriptorGenerator#createDescriptor(java.lang.annotation.Annotation,
		 *      java.lang.Class)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected D createDescriptor(A annotation, Class<?> beanClass) {
			ArcteryxCachedBean bean = (ArcteryxCachedBean) annotation;
			CachedBeanDescriptor descriptor = new CachedBeanDescriptor();
			descriptor.setName(bean.name());
			descriptor.setBeanClass(beanClass);
			descriptor.setDescription(bean.description());
			descriptor.setCacheName(bean.cacheName());
			descriptor.setDefaultSorterCode(bean.defaultSorterCode());
			return (D) descriptor;
		}
	}

	public static class SpringCachedBeanDescriptorGenerator extends
			CachedBeanDescriptorGenerator<ArcteryxSpringCachedBean, SpringCachedBeanDescriptor> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.BeanDescriptorGenerator#getSupportedClass()
		 */
		@Override
		public Class<ArcteryxSpringCachedBean> getSupportedClass() {
			return ArcteryxSpringCachedBean.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.CachedBeanDescriptorGenerator#getAnnotation(java.lang.Class)
		 */
		@Override
		protected ArcteryxSpringCachedBean getAnnotation(Class<?> beanClass) {
			return beanClass.getAnnotation(ArcteryxSpringCachedBean.class);
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.CachedBeanDescriptorGenerator#createDescriptor(java.lang.annotation.Annotation,
		 *      java.lang.Class)
		 */
		@Override
		protected SpringCachedBeanDescriptor createDescriptor(ArcteryxSpringCachedBean annotation, Class<?> beanClass) {
			SpringCachedBeanDescriptor descriptor = new SpringCachedBeanDescriptor();
			descriptor.setName(annotation.name());
			descriptor.setBeanClass(beanClass);
			descriptor.setDescription(annotation.description());
			descriptor.setCacheName(annotation.cacheName());
			descriptor.setDefaultSorterCode(annotation.defaultSorterCode());
			descriptor.setSpringContextId(annotation.springContextId());
			descriptor.setSpringBeanId(annotation.springBeanId());
			return descriptor;
		}
	}

	public static class BeanPropertyDescriptorGenerator implements IBeanPropertyDescriptorGenerator {
		private ArcteryxBeanHanlder handler = null;
		private IBeanPropertyDescriptorGenerator generator = null;

		public BeanPropertyDescriptorGenerator(IBeanPropertyDescriptorGenerator generator) {
			this.generator = generator;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#setHandler(com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder)
		 */
		@Override
		public void setHandler(ArcteryxBeanHanlder handler) {
			this.handler = handler;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#getHandler()
		 */
		@Override
		public ArcteryxBeanHanlder getHandler() {
			return this.handler;
		}

		/**
		 * @return the generator
		 */
		public IBeanPropertyDescriptorGenerator getGenerator() {
			return generator;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#getSupportedClass()
		 */
		@Override
		public Class<? extends Annotation> getSupportedClass() {
			return ArcteryxBeanProperty.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#createDescriptor(java.lang.reflect.Field)
		 */
		@Override
		public IBeanPropertyDescriptor createDescriptor(Field field) throws Exception {
			BeanPropertyDescriptor descriptor = (BeanPropertyDescriptor) (this.getGenerator() != null ? this
					.getGenerator().createDescriptor(field) : new BeanPropertyDescriptor());
			descriptor.setName(field.getName());
			ArcteryxBeanProperty annotation = field.getAnnotation(ArcteryxBeanProperty.class);
			if (annotation != null) {
				descriptor.setDescription(annotation.description());
			}
			return descriptor;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#createDescriptor(java.lang.reflect.Method)
		 */
		@Override
		public IBeanPropertyDescriptor createDescriptor(Method method) throws Exception {
			BeanPropertyDescriptor descriptor = (BeanPropertyDescriptor) (this.getGenerator() != null ? this
					.getGenerator().createDescriptor(method) : new BeanPropertyDescriptor());
			descriptor.setName(ReflectionUtils.getPropertyName(method));
			ArcteryxBeanProperty annotation = method.getAnnotation(ArcteryxBeanProperty.class);
			if (annotation != null) {
				descriptor.setDescription(annotation.description());
			}
			return descriptor;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#readAdvanced(com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor)
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void readAdvanced(IBeanPropertyDescriptor property) throws Exception {
			BeanPropertyDescriptor descriptor = (BeanPropertyDescriptor) property;

			String propertyName = property.getName();
			Class<?> beanClass = property.getBeanDescriptor().getBeanClass();
			Field field = ReflectionUtils.getField(beanClass, propertyName);
			Method getter = ReflectionUtils.getGetter(beanClass, propertyName);
			Method setter = ReflectionUtils.getSetter(beanClass, propertyName);

			// reorganizer
			Annotation reorganizer = getConstraintReorganizer(field, getter, setter);
			if (reorganizer != null) {
				descriptor.setConstraintReorganizer((IBeanPropertyConstraintReorganizer) getHandler()
						.getConstraintReorganizerGenerator(
								reorganizer.annotationType().getAnnotation(ConstraintReorganizer.class)
										.reorganizerClass()).createReorganizer(reorganizer));
			}
			// constraints
			Annotation[] annotations = getAnnotations(field, getter, setter);
			List<IConstraint> constraints = generateConstraints(annotations);
			if (constraints != null && constraints.size() != 0) {
				descriptor.setConstraints(this.convertToPropertyConstraints(constraints));
			}

			// default value
			DefaultValue defaultValue = getDefaultValue(field, getter, setter);
			if (defaultValue != null) {
				descriptor.setDefaultValue(defaultValue.value());
				descriptor.setDefaultValueFormat(defaultValue.format());
			}
		}

		/**
		 * get default value annotation
		 * 
		 * @param field
		 * @param getter
		 * @param setter
		 * @return
		 */
		protected DefaultValue getDefaultValue(Field field, Method getter, Method setter) {
			DefaultValue dv1 = field == null ? null : field.getAnnotation(DefaultValue.class);
			DefaultValue dv2 = getter == null ? null : getter.getAnnotation(DefaultValue.class);
			DefaultValue dv3 = setter == null ? null : setter.getAnnotation(DefaultValue.class);
			if (dv1 == null && dv2 == null && dv3 == null) {
				return null;
			} else if ((dv1 != null && (dv2 != null || dv3 != null)) || (dv2 != null && (dv1 != null || dv3 != null))
					|| (dv3 != null && (dv1 != null || dv2 != null))) {
				throw new AnnotationDefineException("Default value @ property [" + field.getName() + "@"
						+ field.getType().getName() + "] defines repeated, only one is allowed.");
			} else {
				return dv1 == null ? (dv2 == null ? dv3 : dv2) : dv1;
			}
		}

		/**
		 * convert to bean constraint list
		 * 
		 * @param constraints
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		protected List<IBeanPropertyConstraint> convertToPropertyConstraints(List<IConstraint> constraints) {
			List<IBeanPropertyConstraint> list = new ArrayList<IBeanPropertyConstraint>(constraints.size());
			for (IConstraint constraint : constraints) {
				list.add((IBeanPropertyConstraint) constraint);
			}
			return list;
		}

		/**
		 * get all annotations in field/getter/setter
		 * 
		 * @param field
		 * @param getter
		 * @param setter
		 * @return
		 */
		protected Annotation[] getAnnotations(Field field, Method getter, Method setter) {
			Annotation[] a1 = field == null ? null : field.getAnnotations();
			Annotation[] a2 = getter == null ? null : getter.getAnnotations();
			Annotation[] a3 = setter == null ? null : setter.getAnnotations();

			List<Annotation> list = new ArrayList<Annotation>((a1 == null ? 0 : a1.length)
					+ (a2 == null ? 0 : a2.length) + (a3 == null ? 0 : a3.length));
			if (a1 != null) {
				for (Annotation a : a1) {
					list.add(a);
				}
			}
			if (a2 != null) {
				for (Annotation a : a2) {
					list.add(a);
				}
			}
			if (a3 != null) {
				for (Annotation a : a3) {
					list.add(a);
				}
			}
			return list.toArray(new Annotation[list.size()]);
		}
	}

	/**
	 * constraint reorganizer generator
	 * 
	 * @author brad.wu
	 *
	 */
	public static interface IConstraintReorganizerGenerator<A extends Annotation> {
		/**
		 * get supported class
		 * 
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		Class<? extends IConstraintReorganizer> getSupportedClass();

		/**
		 * create reorganizer
		 * 
		 * @param annotation
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		IConstraintReorganizer createReorganizer(A annotation);
	}

	public static class BeanConstraintReorganizerGenerator implements
			IConstraintReorganizerGenerator<BeanConstraintReorganizer> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IConstraintReorganizerGenerator#getSupportedClass()
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public Class<? extends IConstraintReorganizer> getSupportedClass() {
			return com.github.nest.arcteryx.meta.beans.internal.validators.BeanConstraintReorganizer.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IConstraintReorganizerGenerator#createReorganizer(java.lang.annotation.Annotation)
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public IConstraintReorganizer createReorganizer(BeanConstraintReorganizer annotation) {
			com.github.nest.arcteryx.meta.beans.internal.validators.BeanConstraintReorganizer reorganizer = new com.github.nest.arcteryx.meta.beans.internal.validators.BeanConstraintReorganizer();
			String[] names = annotation.names();
			if (names != null) {
				reorganizer.setSkippedConstraintNames(Arrays.asList(names));
			}
			Class<?>[] types = annotation.types();
			if (types != null) {
				List<Class<? extends IBeanConstraint>> list = new LinkedList<Class<? extends IBeanConstraint>>();
				for (Class<?> type : types) {
					if (IBeanConstraint.class.isAssignableFrom(type)) {
						list.add((Class<? extends IBeanConstraint>) type);
					} else if (type.isAnnotation()) {
						list.add((Class<? extends IBeanConstraint>) type.getAnnotation(Constraint.class)
								.constraintClass());
					} else {
						throw new AnnotationDefineException("Type [" + type
								+ "] of BeanConstraintReorganizer is not accepted.");
					}
				}
				reorganizer.setSkippedConstraintTypes(list);
			}
			reorganizer.setOverwrite(annotation.overwrite());
			return reorganizer;
		}
	}

	public static class BeanPropetyConstraintReorganizerGenerator implements
			IConstraintReorganizerGenerator<BeanPropertyConstraintReorganizer> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IConstraintReorganizerGenerator#getSupportedClass()
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public Class<? extends IConstraintReorganizer> getSupportedClass() {
			return com.github.nest.arcteryx.meta.beans.internal.validators.BeanPropertyConstraintReorganizer.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IConstraintReorganizerGenerator#createReorganizer(java.lang.annotation.Annotation)
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public IConstraintReorganizer createReorganizer(BeanPropertyConstraintReorganizer annotation) {
			com.github.nest.arcteryx.meta.beans.internal.validators.BeanPropertyConstraintReorganizer reorganizer = new com.github.nest.arcteryx.meta.beans.internal.validators.BeanPropertyConstraintReorganizer();
			String[] names = annotation.names();
			if (names != null) {
				reorganizer.setSkippedConstraintNames(Arrays.asList(names));
			}
			Class<?>[] types = annotation.types();
			if (types != null) {
				List<Class<? extends IBeanPropertyConstraint>> list = new LinkedList<Class<? extends IBeanPropertyConstraint>>();
				for (Class<?> type : types) {
					if (IBeanConstraint.class.isAssignableFrom(type)) {
						list.add((Class<? extends IBeanPropertyConstraint>) type);
					} else if (type.isAnnotation()) {
						list.add((Class<? extends IBeanPropertyConstraint>) type.getAnnotation(Constraint.class)
								.constraintClass());
					} else {
						throw new AnnotationDefineException("Type [" + type
								+ "] of BeanPropertyConstraintReorganizer is not accepted.");
					}
				}
				reorganizer.setSkippedConstraintTypes(list);
			}
			reorganizer.setOverwrite(annotation.overwrite());
			return reorganizer;
		}
	}
}