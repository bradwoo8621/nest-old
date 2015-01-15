/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.util.ArrayList;
import java.util.Collections;
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
		if (violations == null) {
			return Collections.emptyList();
		} else {
			List<IConstraintViolation> list = new ArrayList<IConstraintViolation>(violations.size());
			for (ConstraintViolation violation : violations) {
				list.add((IConstraintViolation) violation);
			}
			return list;
		}
	}
}
