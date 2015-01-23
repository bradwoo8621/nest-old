/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.constraints;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.beans.ConstraintLevel;
import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * abstract constraint
 * 
 * @author brad.wu
 */
public abstract class AbstractConstraint<ConstraintAnnotatoin extends Annotation> implements
		IConstraint<ConstraintAnnotatoin> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String name = null;
	private String errorCode = null;
	private String messageTemplate = null;
	private String when = null;
	private String[] profiles = null;
	private ConstraintSeverity severity = ConstraintSeverity.defaultSeverity();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getMessageTemplate()
	 */
	@Override
	public String getMessageTemplate() {
		return StringUtils.isBlank(this.messageTemplate) ? this.createDefaultMessageTemplate() : this.messageTemplate;
	}

	/**
	 * to standardize the message template
	 * 
	 * @return
	 */
	protected String createDefaultMessageTemplate() {
		return getClass().getSimpleName() + ".violated";
	}

	/**
	 * @param messageTemplate
	 *            the messageTemplate to set
	 */
	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getWhen()
	 */
	@Override
	public String getWhen() {
		return when;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	public void setWhen(String when) {
		this.when = when;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getErrorCode()
	 */
	@Override
	public String getErrorCode() {
		return StringUtils.isBlank(this.errorCode) ? this.createDefaultErrorCode() : this.errorCode;
	}

	/**
	 * to standardize the error code
	 * 
	 * @return
	 */
	protected String createDefaultErrorCode() {
		return getClass().getSimpleName();
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getProfiles()
	 */
	@Override
	public String[] getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles
	 *            the profiles to set
	 */
	public void setProfiles(String[] profiles) {
		this.profiles = profiles;
	}

	/**
	 * set profile
	 * 
	 * @param profile
	 */
	public void setProfile(String profile) {
		if (StringUtils.isBlank(profile)) {
			this.profiles = null;
		} else {
			this.profiles = new String[] { profile };
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getSeverity()
	 */
	@Override
	public ConstraintSeverity getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(ConstraintSeverity severity) {
		this.severity = severity;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#getLevel()
	 */
	@Override
	public ConstraintLevel getLevel() {
		return ConstraintLevel.BEAN;
	}

	/**
	 * return super.toString();
	 * 
	 * @return
	 */
	protected final String originalToString() {
		return super.toString();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#configure(java.lang.annotation.Annotation)
	 */
	@Override
	public void configure(ConstraintAnnotatoin annotation) {
		Class<?> annotationClass = annotation.getClass();
		this.setName((String) getValue(getMethod(annotationClass, "name"), annotation));
		this.setErrorCode((String) getValue(getMethod(annotationClass, "errorCode"), annotation));
		this.setMessageTemplate((String) getValue(getMethod(annotationClass, "messageTemplate"), annotation));
		this.setProfiles((String[]) getValue(getMethod(annotationClass, "profiles"), annotation));
		this.setSeverity((ConstraintSeverity) getValue(getMethod(annotationClass, "severity"), annotation));
		this.setWhen((String) getValue(getMethod(annotationClass, "when"), annotation));
	}

	protected Method getMethod(Class<?> clazz, String methodName) {
		try {
			return clazz.getDeclaredMethod(methodName, (Class<?>[]) null);
		} catch (SecurityException e) {
			if (logger.isWarnEnabled())
				logger.warn("Method [" + methodName + "] not found in class [" + clazz.getName() + "].", e);
		} catch (NoSuchMethodException e) {
			if (logger.isWarnEnabled())
				logger.warn("Method [" + methodName + "] not found in class [" + clazz.getName() + "].", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	protected <T> T getValue(Method method, ConstraintAnnotatoin annotation) {
		try {
			return (T) method.invoke(annotation, (Object[]) null);
		} catch (IllegalArgumentException e) {
			if (logger.isErrorEnabled())
				logger.error("Failed to get value by method [" + method.getClass().getName() + "#" + method.getName()
						+ "].", e);
		} catch (IllegalAccessException e) {
			if (logger.isErrorEnabled())
				logger.error("Failed to get value by method [" + method.getClass().getName() + "#" + method.getName()
						+ "].", e);
		} catch (InvocationTargetException e) {
			if (logger.isErrorEnabled())
				logger.error("Failed to get value by method [" + method.getClass().getName() + "#" + method.getName()
						+ "].", e);
		}
		return null;
	}
}
