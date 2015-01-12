/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import net.sf.oval.configuration.Configurer;
import net.sf.oval.configuration.pojo.elements.ClassConfiguration;
import net.sf.oval.configuration.pojo.elements.ConstraintSetConfiguration;

/**
 * OVal configurer
 * 
 * @author brad.wu
 */
public interface IOValConfigurer extends Configurer {
	/**
	 * add constraint set configuration
	 * 
	 * @param constraintSetConfiguration
	 */
	void addConstraintSetConfiguration(ConstraintSetConfiguration constraintSetConfiguration);

	/**
	 * add class configuration
	 * 
	 * @param classConfiguration
	 */
	void addClassConfiguration(ClassConfiguration classConfiguration);

}
