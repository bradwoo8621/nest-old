/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.util.ReflectionUtils;

/**
 * resource property descriptor generator
 * 
 * @author brad.wu
 */
public interface IResourcePropertyDescriptorGenerator {
	/**
	 * set aware
	 * 
	 * @param aware
	 */
	void setAware(ArcteryxResourceAware aware);

	/**
	 * get aware
	 * 
	 * @return
	 */
	ArcteryxResourceAware getAware();

	/**
	 * create property descriptor by field
	 * 
	 * @param property
	 * @param annotation
	 * @return
	 * @throws Exception
	 */
	IPropertyDescriptor createDescriptor(IPropertyDeclaration property, Annotation annotation) throws Exception;

	/**
	 * property, including a set of fields and methods
	 * 
	 * @author brad.wu
	 */
	public static interface IPropertyDeclaration {
		/**
		 * get fields
		 * 
		 * @return
		 */
		Set<Field> getFields();

		/**
		 * get methods
		 * 
		 * @return
		 */
		Set<Method> getMethods();

		/**
		 * add method
		 * 
		 * @param method
		 */
		void addMethod(Method method);

		/**
		 * add field
		 * 
		 * @param field
		 */
		void addField(Field field);

		/**
		 * is property or not
		 * 
		 * @return
		 */
		boolean isProperty();

		/**
		 * get declared annotation, must call after {@linkplain #isProperty()},
		 * or return null.
		 * 
		 * @return
		 */
		Annotation getDeclaredAnnotation();

		/**
		 * get property name
		 * 
		 * @return
		 */
		String getPropertyName();

		/**
		 * get declared class
		 * 
		 * @return
		 */
		Class<?> getDeclaringClass();
	}

	/**
	 * property implementation
	 * 
	 * @author brad.wu
	 */
	public static class PropertyDeclaration implements IPropertyDeclaration {
		private Set<Field> fields = null;
		private Set<Method> methods = null;
		private Annotation annotation = null;

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#getFields()
		 */
		@Override
		public Set<Field> getFields() {
			return this.fields;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#getMethods()
		 */
		@Override
		public Set<Method> getMethods() {
			return this.methods;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#addMethod(java.lang.reflect.Method)
		 */
		@Override
		public void addMethod(Method method) {
			if (methods == null) {
				methods = new HashSet<Method>();
			}
			methods.add(method);
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#addField(java.lang.reflect.Field)
		 */
		@Override
		public void addField(Field field) {
			if (fields == null) {
				fields = new HashSet<Field>();
			}
			fields.add(field);
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#getDeclaredAnnotation()
		 */
		@Override
		public Annotation getDeclaredAnnotation() {
			return this.annotation;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#getPropertyName()
		 */
		@Override
		public String getPropertyName() {
			if (fields != null) {
				for (Field field : fields) {
					return field.getName();
				}
			}
			if (methods != null) {
				for (Method method : methods) {
					return ReflectionUtils.getPropertyName(method);
				}
			}
			return null;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#getDeclaringClass()
		 */
		@Override
		public Class<?> getDeclaringClass() {
			if (fields != null) {
				for (Field field : fields) {
					return field.getDeclaringClass();
				}
			}
			if (methods != null) {
				for (Method method : methods) {
					return method.getDeclaringClass();
				}
			}
			return null;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration#isProperty()
		 */
		@Override
		public boolean isProperty() {
			int times = 0;
			if (fields != null) {
				for (Field field : fields) {
					Annotation[] annotations = field.getAnnotations();
					if (annotations != null) {
						for (Annotation annotation : annotations) {
							if (annotation.annotationType().isAnnotationPresent(ArcteryxResourceProperty.class)) {
								this.annotation = annotation;
								times++;
							}
						}
					}
				}
			}
			if (methods != null) {
				for (Method method : methods) {
					Annotation[] annotations = method.getAnnotations();
					if (annotations != null) {
						for (Annotation annotation : annotations) {
							if (annotation.annotationType().isAnnotationPresent(ArcteryxResourceProperty.class)) {
								this.annotation = annotation;
								times++;
							}
						}
					}
				}
			}
			if (times > 1) {
				throw buildException();
			} else {
				return times == 1;
			}
		}

		protected AnnotationDefineException buildException() {
			StringBuilder fieldStr = new StringBuilder("Field [");
			String className = this.getDeclaringClass().getName();
			if (fields != null) {
				for (Field field : fields) {
					fieldStr.append(field.getName()).append(',');
				}
				if (fieldStr.length() != 8) {
					fieldStr.deleteCharAt(fieldStr.length() - 1).append(']');
				}
			}
			StringBuilder methodStr = new StringBuilder("Method [");
			if (methods != null) {
				for (Method method : methods) {
					methodStr.append(method.getName()).append(',');
				}
				if (methodStr.length() != 8) {
					methodStr.deleteCharAt(methodStr.length() - 1).append(']');
				}
			}
			StringBuilder sb = new StringBuilder("For class [" + className + "], ");
			if (fieldStr.length() != 7) {
				sb.append(fieldStr.toString());
			}
			if (methodStr.length() != 8) {
				sb.append(methodStr.toString());
			}

			return new AnnotationDefineException(sb.toString() + ", more than one property annotation defined.");
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "PropertyDeclaration [fields=" + fields + ", methods=" + methods + "]";
		}
	}
}
