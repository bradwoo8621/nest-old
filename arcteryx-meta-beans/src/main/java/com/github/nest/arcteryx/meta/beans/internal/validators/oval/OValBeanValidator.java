/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.Configurer;

import com.github.nest.arcteryx.meta.beans.internal.AbstractBeanValidator;

/**
 * OVal bean validator, base one OVal.
 * 
 * @author brad.wu
 */
public class OValBeanValidator extends AbstractBeanValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T validate(Object resource) {
		List<ConstraintViolation> result = new Validator(getConfiguration()).validate(resource);
		return (T) result;
	}

	/**
	 * get validation configuration
	 * 
	 * @return
	 */
	protected Configurer getConfiguration() {
		return this.getBeanDescriptor().getBeanDescriptorContext().getValidationConfiguration().getRealConfiguration();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T validate(Object resource, String... profiles) {
		List<ConstraintViolation> result = new Validator(getConfiguration()).validate(resource, profiles);
		return (T) result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.Class[])
	 */
	@Override
	public <T> T validate(Object resource, Class<?>... groups) {
		Set<String> profiles = new HashSet<String>();
		if (groups != null) {
			for (Class<?> group : groups) {
				profiles.add(group.getName());
			}
		}
		if (profiles.size() == 0) {
			return validate(resource);
		} else {
			return validate(resource, profiles.toArray(new String[profiles.size()]));
		}
	}
}
