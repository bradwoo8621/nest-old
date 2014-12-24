/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.Check;
import net.sf.oval.configuration.pojo.elements.ConstraintSetConfiguration;

/**
 * wrapper of {@link ConstraintSetConfiguration}, add getter and setter
 * 
 * @author brad.wu
 */
public class ConstraintSetConfigurationWrapper extends ConstraintSetConfiguration {
	private static final long serialVersionUID = 1L;

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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
