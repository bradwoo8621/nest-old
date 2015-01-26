/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.IBeanCreator;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * Bean creator
 * 
 * @author brad.wu
 */
public class BeanCreator extends AbstractStaticCodeBeanOperator implements IBeanCreator {
	private Map<String, PropertyDescriptor> propertyDescriptorMap = null;
	private Map<Class<?>, ValueTypeConvertor> typeConvertors = new HashMap<Class<?>, ValueTypeConvertor>();

	public BeanCreator() {
		initValueTypeConvertors();
	}

	/**
	 * init value type convertors
	 */
	protected void initValueTypeConvertors() {
		this.addValueTypeConvertor(new PrimitiveIntConvertor());
		this.addValueTypeConvertor(new PrimitiveShortConvertor());
		this.addValueTypeConvertor(new PrimitiveLongConvertor());
		this.addValueTypeConvertor(new PrimitiveFloatConvertor());
		this.addValueTypeConvertor(new PrimitiveDoubleConvertor());
		this.addValueTypeConvertor(new PrimitiveByteConvertor());
		this.addValueTypeConvertor(new PrimitiveBooleanConvertor());
		this.addValueTypeConvertor(new PrimitiveCharConvertor());

		this.addValueTypeConvertor(new IntConvertor());
		this.addValueTypeConvertor(new ShortConvertor());
		this.addValueTypeConvertor(new LongConvertor());
		this.addValueTypeConvertor(new FloatConvertor());
		this.addValueTypeConvertor(new DoubleConvertor());
		this.addValueTypeConvertor(new ByteConvertor());
		this.addValueTypeConvertor(new BooleanConvertor());
		this.addValueTypeConvertor(new CharConvertor());

		this.addValueTypeConvertor(new BigIntegerConvertor());
		this.addValueTypeConvertor(new BigDecimalConvertor());
		this.addValueTypeConvertor(new DateConvertor());
		this.addValueTypeConvertor(new SQLDateConvertor());
		this.addValueTypeConvertor(new TimestampConvertor());
	}

	/**
	 * add value type convertor
	 * 
	 * @param convertor
	 */
	protected void addValueTypeConvertor(ValueTypeConvertor convertor) {
		typeConvertors.put(convertor.getSupportedClass(), convertor);
	}

	/**
	 * set value type convertors
	 * 
	 * @param convertors
	 */
	public void setValueTypeConvertors(Set<ValueTypeConvertor> convertors) {
		for (ValueTypeConvertor convertor : convertors) {
			this.addValueTypeConvertor(convertor);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
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
					Method writeMethod = pd.getWriteMethod();
					Class<?> parameterType = writeMethod.getParameterTypes()[0];
					Object value = convertValueType(defaultValue, propertyDescriptor.getDefaultValueFormat(),
							parameterType);
					pd.getWriteMethod().invoke(resource, value);
				}
			} catch (IllegalArgumentException e) {
				throw new ResourceException("Failed to operate value on property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			} catch (IllegalAccessException e) {
				throw new ResourceException("Failed to operate value on property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			} catch (InvocationTargetException e) {
				throw new ResourceException("Failed to operate value on property [" + name + "] of bean ["
						+ getBeanDescriptor().getBeanClass() + "].", e);
			}
		}

		return resource;
	}

	/**
	 * convert default value type
	 * 
	 * @param defaultValue
	 * @param defaultValueFormat
	 * @param parameterType
	 * @return
	 */
	protected Object convertValueType(Object defaultValue, String defaultValueFormat, Class<?> parameterType) {
		ValueTypeConvertor convertor = null;
		if (parameterType.isArray()) {
			convertor = typeConvertors.get(parameterType.getComponentType());
		} else {
			convertor = typeConvertors.get(parameterType);
		}
		if (convertor != null) {
			return convertor.convert((String) defaultValue, defaultValueFormat, parameterType.isArray());
		} else {
			return defaultValue;
		}
	}

	public static interface ValueTypeConvertor {
		/**
		 * get supported class
		 * 
		 * @return
		 */
		Class<?> getSupportedClass();

		/**
		 * convert value type
		 * 
		 * @param value
		 * @param format
		 * @param isArray
		 * @return
		 */
		Object convert(String value, String format, boolean isArray);
	}

	public static abstract class AbstractValueTypeConvertor implements ValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String, boolean)
		 */
		@Override
		public Object convert(String value, String format, boolean isArray) {
			if (isArray) {
				if (StringUtils.isEmpty(value)) {
					return Array.newInstance(getSupportedClass(), 0);
				} else {
					String[] values = value.split(separator());
					Object array = Array.newInstance(getSupportedClass(), values.length);
					for (int index = 0, count = values.length; index < count; index++) {
						Array.set(array, index, convert(values[index], format));
					}
					return array;
				}
			} else {
				return convert(value, format);
			}
		}

		/**
		 * convert value
		 * 
		 * @param value
		 * @param format
		 * @return
		 */
		protected abstract Object convert(String value, String format);

		/**
		 * get split sign
		 * 
		 * @return
		 */
		protected String separator() {
			return ",";
		}
	}

	public static class PrimitiveIntConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return int.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? 0 : Integer.parseInt(value);
		}
	}

	public static class PrimitiveShortConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return short.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? 0 : Short.parseShort(value);
		}
	}

	public static class PrimitiveLongConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return long.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? 0 : Long.parseLong(value);
		}
	}

	public static class PrimitiveFloatConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return float.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? 0 : Float.parseFloat(value);
		}
	}

	public static class PrimitiveDoubleConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return double.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? 0 : Double.parseDouble(value);
		}
	}

	public static class PrimitiveBooleanConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return boolean.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? false : Boolean.parseBoolean(value);
		}
	}

	public static class PrimitiveByteConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return byte.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? 0 : Byte.parseByte(value);
		}
	}

	public static class PrimitiveCharConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return char.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isEmpty(value) ? 0 : value.charAt(0);
		}
	}

	public static class IntConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Integer.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
		}
	}

	public static class ShortConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Short.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Short.valueOf(value);
		}
	}

	public static class LongConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Long.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Long.valueOf(value);
		}
	}

	public static class FloatConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Float.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Float.valueOf(value);
		}
	}

	public static class DoubleConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Double.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Double.valueOf(value);
		}
	}

	public static class BooleanConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Boolean.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Boolean.valueOf(value);
		}
	}

	public static class ByteConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Byte.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : Byte.valueOf(value);
		}
	}

	public static class CharConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Character.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isEmpty(value) ? null : value.charAt(0);
		}
	}

	public static class BigIntegerConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return BigInteger.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : new BigInteger(value);
		}
	}

	public static class BigDecimalConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return BigDecimal.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			return StringUtils.isBlank(value) ? null : new BigDecimal(value);
		}
	}

	public static class DateConvertor extends AbstractValueTypeConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Date.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			if (StringUtils.isBlank(value)) {
				return null;
			} else if (value.equalsIgnoreCase("now")) {
				return new Date();
			} else {
				try {
					return new SimpleDateFormat(format).parse(value);
				} catch (ParseException e) {
					throw new ResourceException("Failed to convert [" + value + "] to date by format [" + format + "].");
				}
			}
		}
	}

	public static class SQLDateConvertor extends DateConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return java.sql.Date.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			Date date = (Date) super.convert(value, format);
			return date == null ? null : new java.sql.Date(date.getTime());
		}
	}

	public static class TimestampConvertor extends DateConvertor {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.ValueTypeConvertor#getSupportedClass()
		 */
		@Override
		public Class<?> getSupportedClass() {
			return Timestamp.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.BeanCreator.AbstractValueTypeConvertor#convert(java.lang.String,
		 *      java.lang.String)
		 */
		@Override
		protected Object convert(String value, String format) {
			Date date = (Date) super.convert(value, format);
			return date == null ? null : new Timestamp(date.getTime());
		}
	}
}
