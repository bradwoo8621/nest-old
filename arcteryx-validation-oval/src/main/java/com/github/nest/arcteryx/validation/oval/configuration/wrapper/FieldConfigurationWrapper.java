/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.Check;
import net.sf.oval.configuration.pojo.elements.FieldConfiguration;

/**
 * wrapper of {@link FieldConfiguration}
 * 
 * @author brad.wu
 */
public class FieldConfigurationWrapper extends FieldConfiguration {
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
	 * @return the checks
	 */
	public List<Check> getChecks() {
		return checks;
	}

	/**
	 * @param checks
	 *            the checks to set
	 */
	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	/**
	 * add check
	 * 
	 * @param check
	 */
	public void addCheck(Check check) {
		if (this.checks == null) {
			synchronized (this) {
				if (this.checks == null) {
					this.checks = new ArrayList<Check>();
				}
			}
		}
		this.checks.add(check);
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
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder(super.toString()).append('@').append(getName()).toString();
	}
}
