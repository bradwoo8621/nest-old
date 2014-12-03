/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.HashSet;
import java.util.Set;

import net.sf.oval.configuration.pojo.elements.ClassConfiguration;
import net.sf.oval.configuration.pojo.elements.ConstructorConfiguration;
import net.sf.oval.configuration.pojo.elements.FieldConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodConfiguration;
import net.sf.oval.configuration.pojo.elements.ObjectConfiguration;

/**
 * wrapper of {@link ClassConfiguration}
 * 
 * @author brad.wu
 */
public class ClassConfigurationWrapper extends ClassConfiguration {
	private static final long serialVersionUID = 1L;

	/**
	 * @return the type
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Class<?> type) {
		this.type = type;
	}

	/**
	 * @return the objectConfiguration
	 */
	public ObjectConfiguration getObjectConfiguration() {
		return objectConfiguration;
	}

	/**
	 * @param objectConfiguration
	 *            the objectConfiguration to set
	 */
	public void setObjectConfiguration(ObjectConfiguration objectConfiguration) {
		this.objectConfiguration = objectConfiguration;
	}

	/**
	 * @return the fieldConfigurations
	 */
	public Set<FieldConfiguration> getFieldConfigurations() {
		return fieldConfigurations;
	}

	/**
	 * @param fieldConfigurations
	 *            the fieldConfigurations to set
	 */
	public void setFieldConfigurations(Set<FieldConfiguration> fieldConfigurations) {
		this.fieldConfigurations = fieldConfigurations;
	}

	/**
	 * add field configuration
	 * 
	 * @param fieldConfiguration
	 */
	public void addFieldConfiguration(FieldConfiguration fieldConfiguration) {
		if (this.fieldConfigurations == null) {
			synchronized (this) {
				if (this.fieldConfigurations == null) {
					this.fieldConfigurations = new HashSet<FieldConfiguration>();
				}
			}
		}
		this.fieldConfigurations.add(fieldConfiguration);
	}

	/**
	 * @return the constructorConfigurations
	 */
	public Set<ConstructorConfiguration> getConstructorConfigurations() {
		return constructorConfigurations;
	}

	/**
	 * @param constructorConfigurations
	 *            the constructorConfigurations to set
	 */
	public void setConstructorConfigurations(Set<ConstructorConfiguration> constructorConfigurations) {
		this.constructorConfigurations = constructorConfigurations;
	}

	/**
	 * add constructor configuration
	 * 
	 * @param constructorConfiguration
	 */
	public void addConstructorConfiguration(ConstructorConfiguration constructorConfiguration) {
		if (this.constructorConfigurations == null) {
			synchronized (this) {
				if (this.constructorConfigurations == null) {
					this.constructorConfigurations = new HashSet<ConstructorConfiguration>();
				}
			}
		}
		this.constructorConfigurations.add(constructorConfiguration);
	}

	/**
	 * @return the methodConfigurations
	 */
	public Set<MethodConfiguration> getMethodConfigurations() {
		return methodConfigurations;
	}

	/**
	 * @param methodConfigurations
	 *            the methodConfigurations to set
	 */
	public void setMethodConfigurations(Set<MethodConfiguration> methodConfigurations) {
		this.methodConfigurations = methodConfigurations;
	}

	/**
	 * add method configuration
	 * 
	 * @param methodConfiguration
	 */
	public void addMethodConfiguration(MethodConfiguration methodConfiguration) {
		if (this.methodConfigurations == null) {
			synchronized (this) {
				if (this.methodConfigurations == null) {
					this.methodConfigurations = new HashSet<MethodConfiguration>();
				}
			}
		}
		this.methodConfigurations.add(methodConfiguration);
	}

	/**
	 * @return the applyFieldConstraintsToConstructors
	 */
	public Boolean getApplyFieldConstraintsToConstructors() {
		return applyFieldConstraintsToConstructors;
	}

	/**
	 * @param applyFieldConstraintsToConstructors
	 *            the applyFieldConstraintsToConstructors to set
	 */
	public void setApplyFieldConstraintsToConstructors(Boolean applyFieldConstraintsToConstructors) {
		this.applyFieldConstraintsToConstructors = applyFieldConstraintsToConstructors;
	}

	/**
	 * @return the applyFieldConstraintsToSetters
	 */
	public Boolean getApplyFieldConstraintsToSetters() {
		return applyFieldConstraintsToSetters;
	}

	/**
	 * @param applyFieldConstraintsToSetters
	 *            the applyFieldConstraintsToSetters to set
	 */
	public void setApplyFieldConstraintsToSetters(Boolean applyFieldConstraintsToSetters) {
		this.applyFieldConstraintsToSetters = applyFieldConstraintsToSetters;
	}

	/**
	 * @return the assertParametersNotNull
	 */
	public Boolean getAssertParametersNotNull() {
		return assertParametersNotNull;
	}

	/**
	 * @param assertParametersNotNull
	 *            the assertParametersNotNull to set
	 */
	public void setAssertParametersNotNull(Boolean assertParametersNotNull) {
		this.assertParametersNotNull = assertParametersNotNull;
	}

	/**
	 * @return the checkInvariants
	 */
	public Boolean getCheckInvariants() {
		return checkInvariants;
	}

	/**
	 * @param checkInvariants
	 *            the checkInvariants to set
	 */
	public void setCheckInvariants(Boolean checkInvariants) {
		this.checkInvariants = checkInvariants;
	}

	/**
	 * @return the inspectInterfaces
	 */
	public Boolean getInspectInterfaces() {
		return inspectInterfaces;
	}

	/**
	 * @param inspectInterfaces
	 *            the inspectInterfaces to set
	 */
	public void setInspectInterfaces(Boolean inspectInterfaces) {
		this.inspectInterfaces = inspectInterfaces;
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
