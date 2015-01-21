/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.Configurer;

import com.github.nest.arcteryx.meta.beans.IConstraintViolation;

/**
 * for OVal 1.84
 * 
 * @author brad.wu
 */
public class OValBeanValidator184 extends AbstractOValBeanValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.AbstractOValBeanValidator#createValidator(net.sf.oval.configuration.Configurer)
	 */
	@Override
	protected Validator createValidator(Configurer configurer) {
		return new OValValidator184(configurer);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.AbstractOValBeanValidator#decorate(java.util.List)
	 */
	@Override
	protected List<IConstraintViolation> decorate(List<ConstraintViolation> violations) {
		if (violations == null || violations.size() == 0) {
			return Collections.emptyList();
		} else {
			List<IConstraintViolation> list = new LinkedList<IConstraintViolation>();
			for (ConstraintViolation violation : violations) {
				convertViolation(violation, list);
			}
			return list;
		}
	}

	protected void convertViolation(ConstraintViolation violation, List<IConstraintViolation> list) {
		ConstraintViolation[] causes = violation.getCauses();
		if (causes != null && causes.length != 0) {
			// violation which caused by other violations.
			// skip itself
			for (ConstraintViolation cause : causes) {
				((OValConstraintViolation) cause).setParent((IConstraintViolation) violation);
				convertViolation(cause, list);
			}
		} else {
			list.add((IConstraintViolation) violation);
		}
	}
}
