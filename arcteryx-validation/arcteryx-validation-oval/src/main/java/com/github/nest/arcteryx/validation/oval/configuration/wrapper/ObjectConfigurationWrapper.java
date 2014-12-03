/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.Check;
import net.sf.oval.configuration.pojo.elements.ObjectConfiguration;

/**
 * wrapper of {@link ObjectConfiguration}
 * 
 * @author brad.wu
 */
public class ObjectConfigurationWrapper extends ObjectConfiguration {
	private static final long serialVersionUID = 1L;

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
}
