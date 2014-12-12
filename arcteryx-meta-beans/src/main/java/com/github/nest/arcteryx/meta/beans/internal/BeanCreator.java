/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.meta.beans.IBeanCreator;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * Bean creator
 * 
 * @author brad.wu
 */
public class BeanCreator extends AbstractStaticCodeBeanOperator implements IBeanCreator {
	private Map<String, PropertyDescriptor> propertyDescriptorMap = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
	}

	/**
	 * if the parameter <code>resource</code> is null, call
	 * {@linkplain #create(IResourceDescriptor)}, otherwise call
	 * {@linkplain #fillWithDefaultValues(Object, IResourceDescriptor)}.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object)
	 */
	@Override
	public Object handle(Object resource) {
		if (resource == null) {
			return create();
		} else {
			return fillWithDefaultValues(resource);
		}
	}

	/**
	 * bean must have a constructor without parameter.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T create() {
		Class<?> beanClass = getBeanDescriptor().getBeanClass();
		Object resource;
		try {
			resource = beanClass.newInstance();
		} catch (InstantiationException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (IllegalAccessException e) {
			throw new ResourceException("Cannot construct object.", e);
		}
		// fill resource with default value
		return (T) this.fillWithDefaultValues(resource);
	}

	/**
	 * Will find the first constructor which match the classes of initial
	 * values. if the initial value is null, only the primitive type cannot be
	 * matched.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create(java.lang.Object[])
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> T create(Object... initialValues) {
		if (initialValues == null || initialValues.length == 0) {
			return create();
		}

		Constructor constructor = findConstructorByInitialValues(initialValues);
		if (constructor == null) {
			throw new ResourceException("Constructor not found for bean [" + getBeanDescriptor().getBeanClass()
					+ "] with initial values [" + convertValuesToString(initialValues) + "]");
		}

		Object resource;
		try {
			resource = constructor.newInstance(initialValues);
		} catch (InstantiationException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (IllegalAccessException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (IllegalArgumentException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (InvocationTargetException e) {
			throw new ResourceException("Cannot construct object.", e);
		}

		// fill resource with default value
		return (T) this.fillWithDefaultValues(resource);
	}

	/**
	 * convert values to string
	 * 
	 * @param values
	 * @return
	 */
	protected String convertValuesToString(Object... values) {
		StringBuilder sb = new StringBuilder(16);
		for (int index = 0, count = values.length; index < count; index++) {
			if (index != 0) {
				sb.append(", ");
			}
			Object value = values[index];
			if (value == null) {
				sb.append("Unknown type=null");
			} else {
				sb.append(value.getClass()).append('=').append(value);
			}
		}
		return sb.toString();
	}

	/**
	 * find constructor by given initial values
	 * 
	 * @param descriptor
	 * @param initialValues
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Constructor findConstructorByInitialValues(Object... initialValues) {
		Constructor theRightOne = null;

		int valuesCount = initialValues.length;
		Class<?> beanClass = getBeanDescriptor().getBeanClass();
		Constructor[] constructors = beanClass.getConstructors();
		for (Constructor constructor : constructors) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			if (parameterTypes.length != valuesCount) {
				continue;
			}

			boolean found = true;
			for (int index = 0, count = valuesCount; index < count; index++) {
				Class<?> paramType = parameterTypes[index];
				Object value = initialValues[index];
				if (value == null) {
					if (paramType.isPrimitive()) {
						// null value cannot be set into primitive type
						found = false;
						break;
					}
				} else if (!paramType.isAssignableFrom(value.getClass())) {
					// value class is not the implementation of declared
					// parameter class
					found = false;
					break;
				}
			}

			if (found) {
				theRightOne = constructor;
				break;
			}
		}
		return theRightOne;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create(java.lang.Class[],
	 *      java.lang.Object[])
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> T create(Class<?>[] constructorParameterTypes, Object[] initialValues) {
		assert constructorParameterTypes != null : "Parameter types of constructor cannot be null.";
		assert initialValues != null : "Initial values cannot be null.";
		assert constructorParameterTypes.length == initialValues.length : "Length of constructor parameter types and initial values must be same";

		Constructor constructor = findConstructorByParameterTypes(constructorParameterTypes);
		if (constructor == null) {
			throw new ResourceException("Constructor not found for bean [" + getBeanDescriptor().getBeanClass()
					+ "] with parameter types [" + convertTypesToString(constructorParameterTypes) + "]");
		}

		Object resource;
		try {
			resource = constructor.newInstance(initialValues);
		} catch (InstantiationException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (IllegalAccessException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (IllegalArgumentException e) {
			throw new ResourceException("Cannot construct object.", e);
		} catch (InvocationTargetException e) {
			throw new ResourceException("Cannot construct object.", e);
		}

		// fill resource with default value
		return (T) this.fillWithDefaultValues(resource);
	}

	/**
	 * convert types to string
	 * 
	 * @param values
	 * @return
	 */
	protected String convertTypesToString(Class<?>[] types) {
		StringBuilder sb = new StringBuilder(16);
		for (int index = 0, count = types.length; index < count; index++) {
			if (index != 0) {
				sb.append(", ");
			}
			sb.append(types[index]);
		}
		return sb.toString();
	}

	/**
	 * find constructor by given parameter types
	 * 
	 * @param parameterTypes
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Constructor findConstructorByParameterTypes(Class<?>[] parameterTypes) {
		int paramCount = parameterTypes.length;
		Class<?> beanClass = getBeanDescriptor().getBeanClass();
		Constructor theRigthOne = null;
		Constructor[] constructors = beanClass.getConstructors();
		for (Constructor constructor : constructors) {
			Class<?>[] paramTypes = constructor.getParameterTypes();
			if (paramTypes == null || paramTypes.length != paramCount) {
				continue;
			}

			boolean found = true;
			for (int index = 0; index < paramCount; index++) {
				if (paramTypes[index] != parameterTypes[index]) {
					found = false;
					break;
				}
			}

			if (found) {
				theRigthOne = constructor;
				break;
			}
		}
		return theRigthOne;
	}

	/**
	 * create resource instance first, fill with default values and set initial
	 * values. if there are value in <code>initialValues</code>, will replace
	 * the default value even the initial value is null.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#create(java.util.Map)
	 */
	@Override
	public <T> T create(Map<String, Object> initialValues) {
		T resource = this.create();

		Map<String, PropertyDescriptor> propertyDescriptorMap = getPropertyDescriptors();

		for (String name : initialValues.keySet()) {
			PropertyDescriptor pd = propertyDescriptorMap.get(name);
			try {
				pd.getWriteMethod().invoke(resource, initialValues.get(name));
			} catch (IllegalArgumentException e) {
				throw new ResourceException("Failed to set value into property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			} catch (IllegalAccessException e) {
				throw new ResourceException("Failed to set value into property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			} catch (InvocationTargetException e) {
				throw new ResourceException("Failed to set value into property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			}
		}

		return resource;
	}

	/**
	 * get property descriptors of bean
	 * 
	 * @param descriptor
	 * @throws IntrospectionException
	 */
	private Map<String, PropertyDescriptor> getPropertyDescriptors() {
		if (this.propertyDescriptorMap == null) {
			synchronized (this) {
				if (this.propertyDescriptorMap == null) {
					this.propertyDescriptorMap = new HashMap<String, PropertyDescriptor>();
					BeanInfo info;
					try {
						info = Introspector.getBeanInfo(getBeanDescriptor().getBeanClass());
					} catch (IntrospectionException e) {
						throw new ResourceException("Failed to introspect bean info for class ["
								+ getBeanDescriptor().getBeanClass() + "].");
					}
					for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
						this.propertyDescriptorMap.put(pd.getName(), pd);
					}
				}
			}
		}
		return this.propertyDescriptorMap;
	}

	/**
	 * Only the value of property is null, default value is set. Otherwise, the
	 * default value will be skipped.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCreator#fillWithDefaultValues(java.lang.Object)
	 */
	@Override
	public <T> T fillWithDefaultValues(T resource) {
		assert (resource != null) : "Resource cannot be null.";

		Map<String, PropertyDescriptor> propertyDescriptorMap = getPropertyDescriptors();
		for (IBeanPropertyDescriptor propertyDescriptor : getBeanDescriptor().getBeanProperties()) {
			Object defaultValue = propertyDescriptor.getDefaultValue();
			if (defaultValue == null) {
				continue;
			}

			String name = propertyDescriptor.getName();
			PropertyDescriptor pd = propertyDescriptorMap.get(name);
			try {
				Object currentValue = pd.getReadMethod().invoke(resource);
				if (currentValue == null) {
					pd.getWriteMethod().invoke(resource, defaultValue);
				}
			} catch (IllegalArgumentException e) {
				throw new ResourceException("Failed to operate value about property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			} catch (IllegalAccessException e) {
				throw new ResourceException("Failed to operate value about property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			} catch (InvocationTargetException e) {
				throw new ResourceException("Failed to operate value about property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			}
		}

		return resource;
	}
}
