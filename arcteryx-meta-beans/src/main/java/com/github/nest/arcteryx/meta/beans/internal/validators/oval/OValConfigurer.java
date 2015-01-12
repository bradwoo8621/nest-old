/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.util.HashSet;

import net.sf.oval.configuration.pojo.POJOConfigurer;
import net.sf.oval.configuration.pojo.elements.ClassConfiguration;
import net.sf.oval.configuration.pojo.elements.ConstraintSetConfiguration;

/**
 * oval configurer
 * 
 * @author brad.wu
 */
public class OValConfigurer extends POJOConfigurer implements IOValConfigurer {
	private static final long serialVersionUID = -3719323625376718118L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValConfigurer#addClassConfiguration(net.sf.oval.configuration.pojo.elements.ClassConfiguration)
	 */
	@Override
	public void addClassConfiguration(ClassConfiguration classConfiguration) {
		if (this.classConfigurations == null) {
			synchronized (this) {
				if (this.classConfigurations == null) {
					this.classConfigurations = new HashSet<ClassConfiguration>();
				}
			}
		}
		this.classConfigurations.add(classConfiguration);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValConfigurer#addConstraintSetConfiguration(net.sf.oval.configuration.pojo.elements.ConstraintSetConfiguration)
	 */
	@Override
	public void addConstraintSetConfiguration(ConstraintSetConfiguration constraintSetConfiguration) {
		if (this.constraintSetConfigurations == null) {
			synchronized (this) {
				if (this.constraintSetConfigurations == null) {
					this.constraintSetConfigurations = new HashSet<ConstraintSetConfiguration>();
				}
			}
		}
		this.constraintSetConfigurations.add(constraintSetConfiguration);
	}
}
