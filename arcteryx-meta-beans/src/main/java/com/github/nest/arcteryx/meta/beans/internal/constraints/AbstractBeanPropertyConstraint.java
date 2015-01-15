/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.ConstraintApplyTo;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;

/**
 * bean property constraint
 * 
 * @author brad.wu
 */
public abstract class AbstractBeanPropertyConstraint implements IBeanPropertyConstraint {
	private static final long serialVersionUID = 7692841064131390892L;

	private String errorCode = null;
	private String messageTemplate = null;
	private String when = null;
	private String target = null;
	private String[] profiles = null;
	private ConstraintSeverity severity = ConstraintSeverity.defaultSeverity();
	private ConstraintApplyTo appliesTo = ConstraintApplyTo.defaultApplyTo();

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
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getTarget()
	 */
	@Override
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
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
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getAppliesTo()
	 */
	@Override
	public ConstraintApplyTo getAppliesTo() {
		return this.appliesTo;
	}

	/**
	 * @param appliesTo
	 *            the appliesTo to set
	 */
	public void setAppliesTo(ConstraintApplyTo appliesTo) {
		this.appliesTo = appliesTo;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getConstraintsRecursive()
	 */
	@Override
	public List<IBeanPropertyConstraint> getConstraintsRecursive() {
		List<IBeanPropertyConstraint> list = new LinkedList<IBeanPropertyConstraint>();
		list.add(this);
		return list;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return originalToString() + " [errorCode=" + errorCode + ", messageTemplate=" + messageTemplate + ", when="
				+ when + ", target=" + target + ", profiles=" + Arrays.toString(profiles) + ", severity=" + severity
				+ ", appliesTo=" + appliesTo + "]";
	}
}
