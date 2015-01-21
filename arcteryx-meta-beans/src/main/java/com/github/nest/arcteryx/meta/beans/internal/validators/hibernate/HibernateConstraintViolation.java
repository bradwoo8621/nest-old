/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.IConstraintViolation;

/**
 * hibernate constraint violation
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public class HibernateConstraintViolation implements IConstraintViolation {
	private ConstraintViolation violation = null;

	public HibernateConstraintViolation(ConstraintViolation violation) {
		this.violation = violation;
	}

	/**
	 * @return the violation
	 */
	protected ConstraintViolation getViolation() {
		return violation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getErrorCode()
	 */
	@Override
	public String getErrorCode() {
		return this.getViolation().getConstraintDescriptor().getAnnotation().annotationType().getName();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getMessage()
	 */
	@Override
	public String getMessage() {
		return this.getViolation().getMessage();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getMessageTemplate()
	 */
	@Override
	public String getMessageTemplate() {
		return this.getViolation().getMessageTemplate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getValueToValidate()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValueToValidate() {
		return (T) this.getViolation().getInvalidValue();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getBeanToValidate()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBeanToValidate() {
		return (T) this.getViolation().getRootBean();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getConstraintSeverity()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConstraintSeverity getConstraintSeverity() {
		Set<Class<? extends Payload>> payloads = this.getViolation().getConstraintDescriptor().getPayload();
		if (payloads == null || payloads.size() == 0) {
			return ConstraintSeverity.defaultSeverity();
		} else {
			// get first one
			Class<? extends Payload> payloadClass = payloads.iterator().next();
			if (HibernateSeverity.INFO.class == payloadClass) {
				return ConstraintSeverity.INFO;
			} else if (HibernateSeverity.WARN.class == payloadClass) {
				return ConstraintSeverity.WARN;
			} else if (HibernateSeverity.ERROR.class == payloadClass) {
				return ConstraintSeverity.ERROR;
			} else if (HibernateSeverity.FATAL.class == payloadClass) {
				return ConstraintSeverity.FATAL;
			} else {
				return ConstraintSeverity.defaultSeverity();
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getPath()
	 */
	@Override
	public String getPath() {
		String path = this.getViolation().getPropertyPath().toString();
		if (StringUtils.isBlank(path)) {
			return SELF;
		} else {
			return path;
		}
	}
}
