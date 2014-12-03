/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.configuration.pojo.elements.MethodPreExecutionConfiguration;
import net.sf.oval.guard.PreCheck;

/**
 * wrapper of {@link MethodPreExecutionConfiguration}
 * 
 * @author brad.wu
 */
public class MethodPreExecutionConfigurationWrapper extends MethodPreExecutionConfiguration {
	private static final long serialVersionUID = 1L;

	/**
	 * @return the checks
	 */
	public List<PreCheck> getChecks() {
		return checks;
	}

	/**
	 * @param checks
	 *            the checks to set
	 */
	public void setChecks(List<PreCheck> checks) {
		this.checks = checks;
	}

	/**
	 * add pre check
	 * 
	 * @param check
	 */
	public void addCheck(PreCheck check) {
		if (this.checks == null) {
			synchronized (this) {
				if (this.checks == null) {
					this.checks = new ArrayList<PreCheck>();
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
}
