/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.ConstraintLevel;
import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * abstract constraint
 * 
 * @author brad.wu
 */
public abstract class AbstractConstraint implements IConstraint {
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
		return messageTemplate;
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
		return errorCode;
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
}
