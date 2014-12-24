/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.configuration.pojo.elements.MethodConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodPostExecutionConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodPreExecutionConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodReturnValueConfiguration;
import net.sf.oval.configuration.pojo.elements.ParameterConfiguration;

/**
 * wrapper of {@link MethodConfiguration}
 * 
 * @author brad.wu
 */
public class MethodConfigurationWrapper extends MethodConfiguration {
	private static final long serialVersionUID = 1L;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the isInvariant
	 */
	public Boolean getIsInvariant() {
		return isInvariant;
	}

	/**
	 * @param isInvariant
	 *            the isInvariant to set
	 */
	public void setIsInvariant(Boolean isInvariant) {
		this.isInvariant = isInvariant;
	}

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
	 * @return the returnValueConfiguration
	 */
	public MethodReturnValueConfiguration getReturnValueConfiguration() {
		return returnValueConfiguration;
	}

	/**
	 * @param returnValueConfiguration
	 *            the returnValueConfiguration to set
	 */
	public void setReturnValueConfiguration(MethodReturnValueConfiguration returnValueConfiguration) {
		this.returnValueConfiguration = returnValueConfiguration;
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
	 * @return the postExecutionConfiguration
	 */
	public MethodPostExecutionConfiguration getPostExecutionConfiguration() {
		return postExecutionConfiguration;
	}

	/**
	 * @param postExecutionConfiguration
	 *            the postExecutionConfiguration to set
	 */
	public void setPostExecutionConfiguration(MethodPostExecutionConfiguration postExecutionConfiguration) {
		this.postExecutionConfiguration = postExecutionConfiguration;
	}

	/**
	 * @return the preCheckInvariants
	 */
	public Boolean getPreCheckInvariants() {
		return preCheckInvariants;
	}

	/**
	 * @param preCheckInvariants
	 *            the preCheckInvariants to set
	 */
	public void setPreCheckInvariants(Boolean preCheckInvariants) {
		this.preCheckInvariants = preCheckInvariants;
	}

	/**
	 * @return the preExecutionConfiguration
	 */
	public MethodPreExecutionConfiguration getPreExecutionConfiguration() {
		return preExecutionConfiguration;
	}

	/**
	 * @param preExecutionConfiguration
	 *            the preExecutionConfiguration to set
	 */
	public void setPreExecutionConfiguration(MethodPreExecutionConfiguration preExecutionConfiguration) {
		this.preExecutionConfiguration = preExecutionConfiguration;
	}
}
