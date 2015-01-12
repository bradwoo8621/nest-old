/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
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
		// TODO Auto-generated method stub
		Check check = createCheck(constraint);
		check.setErrorCode(constraint.getErrorCode());
		check.setMessage(constraint.getMessageTemplate());
		check.setProfiles(constraint.getProfiles());
		check.setSeverity(constraint.getSeverity().getSeverity());
		check.setTarget(constraint.getTarget());
		check.setWhen(constraint.getWhen());
		return check;
	}

	/**
	 * create check
	 * 
	 * @param constraint
	 * @return
	 */
	protected abstract Check createCheck(C constraint);
}
