/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.Check;
import net.sf.oval.CheckExclusion;
import net.sf.oval.configuration.pojo.elements.ParameterConfiguration;

/**
 * wrapper of {@link ParameterConfiguration}
 * 
 * @author brad.wu
 */
public class ParameterConfigurationWrapper extends ParameterConfiguration {
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
	 * @return the checkExclusions
	 */
	public List<CheckExclusion> getCheckExclusions() {
		return checkExclusions;
	}

	/**
	 * @param checkExclusions
	 *            the checkExclusions to set
	 */
	public void setCheckExclusions(List<CheckExclusion> checkExclusions) {
		this.checkExclusions = checkExclusions;
	}

	/**
	 * add check exclusion
	 * 
	 * @param checkExclusion
	 */
	public void addCheckExclusion(CheckExclusion checkExclusion) {
		if (this.checkExclusions == null) {
			synchronized (this) {
				if (this.checkExclusions == null) {
					this.checkExclusions = new ArrayList<CheckExclusion>();
				}
			}
		}
		this.checkExclusions.add(checkExclusion);
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
