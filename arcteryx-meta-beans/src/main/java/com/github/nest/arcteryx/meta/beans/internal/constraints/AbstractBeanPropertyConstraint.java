/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * bean property constraint
 * 
 * @author brad.wu
 */
public abstract class AbstractBeanPropertyConstraint implements IBeanPropertyConstraint {
	private static final long serialVersionUID = 7692841064131390892L;

	private IBeanPropertyDescriptor propertyDescriptor = null;
	private String errorCode = null;
	private String messageTemplate = null;
	private String when = null;
	private String target = null;
	private String[] profiles = null;
	private ConstraintSeverity severity = ConstraintSeverity.ERROR;

	public AbstractBeanPropertyConstraint(IBeanPropertyDescriptor propertyDescriptor) {
		this.propertyDescriptor = propertyDescriptor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getPropertyDescriptor()
	 */
	@Override
	public IBeanPropertyDescriptor getPropertyDescriptor() {
		return this.propertyDescriptor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getBeanDescriptor()
	 */
	@Override
	public IBeanDescriptor getBeanDescriptor() {
		return this.getPropertyDescriptor().getBeanDescriptor();
	}

	/**
	 * @return the messageTemplate
	 */
	protected String getMessageTemplate() {
		return messageTemplate;
	}

	/**
	 * @param messageTemplate
	 *            the messageTemplate to set
	 */
	protected void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	/**
	 * @return the when
	 */
	protected String getWhen() {
		return when;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	protected void setWhen(String when) {
		this.when = when;
	}

	/**
	 * @return the target
	 */
	protected String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	protected void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the errorCode
	 */
	protected String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	protected void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the profiles
	 */
	protected String[] getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles
	 *            the profiles to set
	 */
	protected void setProfiles(String[] profiles) {
		this.profiles = profiles;
	}

	/**
	 * @return the severity
	 */
	protected ConstraintSeverity getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	protected void setSeverity(ConstraintSeverity severity) {
		this.severity = severity;
	}
}
