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

import com.github.nest.arcteryx.meta.beans.IConstraintViolation;
import com.github.nest.arcteryx.meta.beans.IValidationConfigurationInitializer;
import com.github.nest.arcteryx.meta.beans.internal.AbstractBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;

/**
 * OVal bean validator, base one OVal.
 * 
 * @author brad.wu
 */
public abstract class AbstractOValBeanValidator extends AbstractBeanValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object)
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource) {
		List<ConstraintViolation> result = createValidator(getConfiguration()).validate(resource);
		return this.decorate(result);
	}

	/**
	 * create validator
	 * 
	 * @param configurer
	 * 
	 * @return
	 */
	protected abstract Validator createValidator(Configurer configurer);

	/**
	 * decorate violation list
	 * 
	 * @param violations
	 * @return
	 */
	protected abstract List<IConstraintViolation> decorate(List<ConstraintViolation> violations);

	/**
	 * get validation configuration
	 * 
	 * @return
	 */
	protected Configurer getConfiguration() {
		IValidationConfiguration configuration = this.getBeanDescriptor().getContext()
				.getInitializedData(IValidationConfigurationInitializer.KEY);
		return configuration.getRealConfiguration();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.String[])
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource, String... profiles) {
		List<ConstraintViolation> result = createValidator(getConfiguration()).validate(resource, profiles);
		return this.decorate(result);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.Class[])
	 */
	@Override
	public List<IConstraintViolation> validate(Object resource, Class<?>... groups) {
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
