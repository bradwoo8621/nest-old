/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanPropertyDescriptor;
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

	private Map<Class<? extends IBeanDescriptor>, IBeanDescriptorGenerator> beanDescriptorGenerators = new HashMap<Class<? extends IBeanDescriptor>, IBeanDescriptorGenerator>();
	private Map<Class<? extends IBeanPropertyDescriptor>, IBeanPropertyDescriptorGenerator> propertyDescriptorGenerators = new HashMap<Class<? extends IBeanPropertyDescriptor>, IBeanPropertyDescriptorGenerator>();

	public ArcteryxBeanHanlder() {
		this.setBeanDescriptorGenerators(initializeGenerators());
	}

	/**
	 * initialize generators
	 * 
	 * @return
	 */
	protected List<IBeanDescriptorGenerator> initializeGenerators() {
		List<IBeanDescriptorGenerator> generators = new LinkedList<IBeanDescriptorGenerator>();
		generators.add(new BeanDescriptorGenerator());
		return generators;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// final Map<String, Object> maps =
		// applicationContext.getBeansWithAnnotation(ArcteryxBean.class);
		String[] names = applicationContext.getBeanNamesForAnnotation(ArcteryxBean.class);

		if (names != null) {
			for (String name : names) {
				Class<?> beanClass = applicationContext.getType(name);
				if (logger.isInfoEnabled()) {
					logger.info("Auto scan bean [" + beanClass.getName() + "]");
				}
				ArcteryxBean bean = beanClass.getAnnotation(ArcteryxBean.class);
				Class<? extends IBeanDescriptor> descriptorClass = bean.descriptorClass();
				IBeanDescriptor descriptor = getBeanDescriptorGenerator(descriptorClass).createDescriptor(beanClass);
				this.getBeanContext().register(descriptor);
			}
		}
	}

	/**
	 * get bean descriptor generator
	 * 
	 * @param descriptorClass
	 * @return
	 */
	protected IBeanDescriptorGenerator getBeanDescriptorGenerator(Class<? extends IBeanDescriptor> descriptorClass) {
		return beanDescriptorGenerators.get(descriptorClass);
	}

	/**
	 * get property descriptor generator
	 * 
	 * @param descriptorClass
	 * @return
	 */
	protected IBeanPropertyDescriptorGenerator getPropertyDescriptorGenerator(
			Class<? extends IBeanPropertyDescriptor> descriptorClass) {
		return propertyDescriptorGenerators.get(descriptorClass);
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
	 * create constraint
	 * 
	 * @param annotation
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static IConstraint createConstraint(Annotation annotation) throws Exception {
		final Constraint constraintAnnotation = annotation.annotationType().getAnnotation(Constraint.class);
		IConstraint constraint = constraintAnnotation.constraintClass().newInstance();
		constraint.configure(annotation);
		return constraint;
	}

	/**
	 * bean descriptor generator
	 * 
	 * @author brad.wu
	 *
	 */
	public static interface IBeanDescriptorGenerator {
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
		Class<? extends IBeanDescriptor> getSupportedClass();

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
		Class<? extends IBeanPropertyDescriptor> getSupportedClass();

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

	/**
	 * bean descriptor generator
	 * 
	 * @author brad.wu
	 */
	public static class BeanDescriptorGenerator implements IBeanDescriptorGenerator {
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
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#getSupportedClass()
		 */
		@Override
		public Class<? extends IBeanDescriptor> getSupportedClass() {
			return BeanDescriptor.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanDescriptorGenerator#createDescriptor(java.lang.Class)
		 */
		@Override
		public IBeanDescriptor createDescriptor(Class<?> beanClass) throws Exception {
			BeanDescriptor descriptor = createDescriptor();
			ArcteryxBean bean = beanClass.getAnnotation(ArcteryxBean.class);
			descriptor.setName(bean.name());
			descriptor.setBeanClass(beanClass);
			descriptor.setDescription(bean.description());
			// TODO how to set parent?
			// constraint reorganizer
			BeanConstraintReorganizer reorganizer = beanClass.getAnnotation(BeanConstraintReorganizer.class);
			if (reorganizer != null) {
				descriptor.setConstraintReorganizer(reorganizer.reorganizerClass().newInstance());
			}
			// constraints
			readTypeConstraints(beanClass, descriptor);
			// properties
			readProperties(beanClass, descriptor);

			return descriptor;
		}

		/**
		 * read properties
		 * 
		 * @param beanClass
		 * @param descriptor
		 * @throws Exception
		 */
		protected void readProperties(Class<?> beanClass, BeanDescriptor descriptor) throws Exception {
			prereadProperties(beanClass, descriptor);
			// loop the created properties and find their field/getter/setter,
			// read the constraints, reorganizer and default value
			Collection<IBeanPropertyDescriptor> properties = descriptor.getBeanProperties();
			for (IBeanPropertyDescriptor property : properties) {
				getHandler().getPropertyDescriptorGenerator(property.getClass()).readAdvanced(property);
			}
		}

		/**
		 * pre-read properties, only name and description is read-in.
		 * 
		 * @param beanClass
		 * @param descriptor
		 * @throws Exception
		 */
		protected void prereadProperties(Class<?> beanClass, BeanDescriptor descriptor) throws Exception {
			List<IPropertyDescriptor> properties = new LinkedList<IPropertyDescriptor>();
			Map<String, Object> map = new HashMap<String, Object>();
			Field[] fields = beanClass.getDeclaredFields();
			for (Field field : fields) {
				ArcteryxBeanProperty annotation = field.getAnnotation(ArcteryxBeanProperty.class);
				if (annotation == null) {
					continue;
				}
				Class<? extends IBeanPropertyDescriptor> descriptorClass = annotation.descriptorClass();
				IBeanPropertyDescriptor property = this.getHandler().getPropertyDescriptorGenerator(descriptorClass)
						.createDescriptor(field);
				properties.add(property);
				map.put(property.getName(), null);
			}
			Method[] methods = beanClass.getDeclaredMethods();
			for (Method method : methods) {
				ArcteryxBeanProperty annotation = method.getAnnotation(ArcteryxBeanProperty.class);
				if (annotation == null) {
					continue;
				}
				Class<? extends IBeanPropertyDescriptor> descriptorClass = annotation.descriptorClass();
				IBeanPropertyDescriptor property = this.getHandler().getPropertyDescriptorGenerator(descriptorClass)
						.createDescriptor(method);
				if (map.containsKey(property.getName())) {
					throw new RuntimeException("Property [" + property.getName() + "@" + beanClass.getName()
							+ "] already defined in other field/method, only one is allowed.");
				}
				properties.add(property);
			}
			descriptor.setProperties(properties);
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
			Annotation[] annotations = beanClass.getAnnotations();
			if (annotations != null && annotations.length != 0) {
				List<IConstraint> constraints = new LinkedList<IConstraint>();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
						constraints.add(createConstraint(annotation));
					} else if (annotation.annotationType().isAnnotationPresent(Constraints.class)) {
						final Method getValue = annotation.annotationType().getDeclaredMethod("values",
								(Class<?>[]) null);
						final Object[] children = (Object[]) getValue.invoke(annotation, (Object[]) null);
						for (Object child : children) {
							constraints.add(createConstraint((Annotation) child));
						}
					}
				}
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

		/**
		 * create descriptor
		 * 
		 * @return
		 */
		protected BeanDescriptor createDescriptor() {
			return new BeanDescriptor();
		}
	}

	public static class BeanPropertyDescriptorGenerator implements IBeanPropertyDescriptorGenerator {
		private ArcteryxBeanHanlder handler = null;

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
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#getSupportedClass()
		 */
		@Override
		public Class<? extends IBeanPropertyDescriptor> getSupportedClass() {
			return BeanPropertyDescriptor.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#createDescriptor(java.lang.reflect.Field)
		 */
		@Override
		public IBeanPropertyDescriptor createDescriptor(Field field) throws Exception {
			BeanPropertyDescriptor descriptor = new BeanPropertyDescriptor();
			descriptor.setName(field.getName());
			ArcteryxBeanProperty annotation = field.getAnnotation(ArcteryxBeanProperty.class);
			descriptor.setDescription(annotation.description());
			return descriptor;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#createDescriptor(java.lang.reflect.Method)
		 */
		@Override
		public IBeanPropertyDescriptor createDescriptor(Method method) throws Exception {
			BeanPropertyDescriptor descriptor = new BeanPropertyDescriptor();
			descriptor.setName(ReflectionUtils.getPropertyName(method));
			ArcteryxBeanProperty annotation = method.getAnnotation(ArcteryxBeanProperty.class);
			descriptor.setDescription(annotation.description());
			return descriptor;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanHanlder.IBeanPropertyDescriptorGenerator#readAdvanced(com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor)
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public void readAdvanced(IBeanPropertyDescriptor property) throws Exception {
			BeanPropertyDescriptor descriptor = (BeanPropertyDescriptor) property;

			String propertyName = property.getName();
			Class<?> beanClass = property.getBeanDescriptor().getBeanClass();
			Field field = ReflectionUtils.getField(beanClass, propertyName);
			Method getter = ReflectionUtils.getGetter(beanClass, propertyName);
			Method setter = ReflectionUtils.getSetter(beanClass, propertyName);

			// reorganizer
			BeanPropertyConstraintReorganizer reorganizer = getConstraintReorganizer(field, getter, setter);
			if (reorganizer != null) {
				descriptor.setConstraintReorganizer(reorganizer.reorganizerClass().newInstance());
			}
			// constraints
			Annotation[] annotations = getAnnotations(field, getter, setter);
			if (annotations != null && annotations.length != 0) {
				List<IConstraint> constraints = new LinkedList<IConstraint>();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
						constraints.add(createConstraint(annotation));
					} else if (annotation.annotationType().isAnnotationPresent(Constraints.class)) {
						final Method getValue = annotation.annotationType().getDeclaredMethod("values",
								(Class<?>[]) null);
						final Object[] children = (Object[]) getValue.invoke(annotation, (Object[]) null);
						for (Object child : children) {
							constraints.add(createConstraint((Annotation) child));
						}
					}
				}
				descriptor.setConstraints(this.convertToPropertyConstraints(constraints));
			}

			// TODO default value
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

		/**
		 * get constraint reorganizer
		 * 
		 * @param field
		 * @param getter
		 * @param setter
		 * @return
		 */
		protected BeanPropertyConstraintReorganizer getConstraintReorganizer(Field field, Method getter, Method setter) {
			BeanPropertyConstraintReorganizer r1 = field == null ? null : field
					.getAnnotation(BeanPropertyConstraintReorganizer.class);
			BeanPropertyConstraintReorganizer r2 = getter == null ? null : getter
					.getAnnotation(BeanPropertyConstraintReorganizer.class);
			BeanPropertyConstraintReorganizer r3 = setter == null ? null : setter
					.getAnnotation(BeanPropertyConstraintReorganizer.class);
			if (r1 == null && r2 == null && r3 == null) {
				return null;
			} else if ((r1 != null && (r2 != null || r3 != null)) || (r2 != null && (r1 != null || r3 != null))
					|| (r3 != null && (r1 != null || r2 != null))) {
				throw new RuntimeException("Constraint reogranizer @ property [" + field.getName() + "@"
						+ field.getType().getName() + "] defines repeated, only one is allowed.");
			} else {
				return r1 == null ? (r2 == null ? r3 : r2) : r1;
			}
		}
	}
}