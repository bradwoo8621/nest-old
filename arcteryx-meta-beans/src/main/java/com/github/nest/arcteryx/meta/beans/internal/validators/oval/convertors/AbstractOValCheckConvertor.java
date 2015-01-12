/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.ConstraintTarget;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint.ApplyTo;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint.Severity;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor;

/**
 * abstract OVal check convertor
 * 
 * @author brad.wu
 */
public abstract class AbstractOValCheckConvertor<C extends IBeanPropertyConstraint> implements IOValCheckConvertor<C> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#convert(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	public Check convert(C constraint) {
		Check check = createCheck(constraint);
		
		check.setErrorCode(constraint.getErrorCode());
		check.setMessage(constraint.getMessageTemplate());
		check.setProfiles(constraint.getProfiles());
		check.setSeverity(convertSeverity(constraint.getSeverity()));
		check.setTarget(constraint.getTarget());
		check.setWhen(constraint.getWhen());
		check.setAppliesTo(convertAppliesTo(constraint.getAppliesTo()));

		return check;
	}

	/**
	 * convert severity to integer value
	 * 
	 * @param severity
	 * @return
	 */
	protected int convertSeverity(Severity severity) {
		if (severity == null) {
			return Severity.WARN.getSeverity();
		} else {
			return severity.getSeverity();
		}
	}

	/**
	 * convert applies to to OVal constraint target
	 * 
	 * @param appliesTo
	 * @return
	 */
	protected ConstraintTarget convertAppliesTo(ApplyTo appliesTo) {
		if (appliesTo == null) {
			return ConstraintTarget.VALUES;
		} else if (ApplyTo.KEYS.equals(appliesTo)) {
			return ConstraintTarget.KEYS;
		} else if (ApplyTo.VALUES.equals(appliesTo)) {
			return ConstraintTarget.VALUES;
		} else if (ApplyTo.CONTAINER.equals(appliesTo)) {
			return ConstraintTarget.CONTAINER;
		}
		throw new BeanValidationException("ApplyTo [" + appliesTo + "] is not supported.");
	}

	/**
	 * create check
	 * 
	 * @param constraint
	 * @return
	 */
	protected abstract Check createCheck(C constraint);
}
