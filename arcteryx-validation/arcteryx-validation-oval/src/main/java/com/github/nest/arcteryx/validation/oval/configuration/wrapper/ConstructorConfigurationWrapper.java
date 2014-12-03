/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.configuration.pojo.elements.ConstructorConfiguration;
import net.sf.oval.configuration.pojo.elements.ParameterConfiguration;

/**
 * wrapper of {@link ConstructorConfiguration}
 * 
 * @author brad.wu
 */
public class ConstructorConfigurationWrapper extends ConstructorConfiguration {
	private static final long serialVersionUID = 1L;

	/**
	 * @return the parameterConfigurations
	 */
	public List<ParameterConfiguration> getParameterConfigurations() {
		return parameterConfigurations;
	}

	/**
	 * @param parameterConfigurations
	 *            the parameterConfigurations to set
	 */
	public void setParameterConfigurations(List<ParameterConfiguration> parameterConfigurations) {
		this.parameterConfigurations = parameterConfigurations;
	}

	/**
	 * add parameter configuration
	 * 
	 * @param parameterConfiguration
	 */
	public void addParameterConfiguration(ParameterConfiguration parameterConfiguration) {
		if (this.parameterConfigurations == null) {
			synchronized (this) {
				if (this.parameterConfigurations == null) {
					this.parameterConfigurations = new ArrayList<ParameterConfiguration>();
				}
			}
		}
		this.parameterConfigurations.add(parameterConfiguration);
	}

	/**
	 * @return the postCheckInvariants
	 */
	public Boolean getPostCheckInvariants() {
		return postCheckInvariants;
	}

	/**
	 * @param postCheckInvariants
	 *            the postCheckInvariants to set
	 */
	public void setPostCheckInvariants(Boolean postCheckInvariants) {
		this.postCheckInvariants = postCheckInvariants;
	}

	/**
	 * @return the overwrite
	 */
	public Boolean getOverwrite() {
		return overwrite;
	}

	/**
	 * @param overwrite
	 *            the overwrite to set
	 */
	public void setOverwrite(Boolean overwrite) {
		this.overwrite = overwrite;
	}
}
