/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.ConstraintTarget;

import com.github.nest.arcteryx.meta.beans.ConstraintApplyTo;
import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor;

/**
 * abstract OVal check convertor
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractOValCheckConvertor<C extends IConstraint> implements IOValCheckConvertor<C> {
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
		check.setWhen(constraint.getWhen());

		return check;
	}

	/**
	 * convert severity to integer value
	 * 
	 * @param severity
	 * @return
	 */
	protected int convertSeverity(ConstraintSeverity severity) {
		if (severity == null) {
			return ConstraintSeverity.WARN.getSeverity();
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
	protected ConstraintTarget convertAppliesTo(ConstraintApplyTo appliesTo) {
		if (appliesTo == null) {
			return ConstraintTarget.VALUES;
		} else if (ConstraintApplyTo.KEYS.equals(appliesTo)) {
			return ConstraintTarget.KEYS;
		} else if (ConstraintApplyTo.VALUES.equals(appliesTo)) {
			return ConstraintTarget.VALUES;
		} else if (ConstraintApplyTo.CONTAINER.equals(appliesTo)) {
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
