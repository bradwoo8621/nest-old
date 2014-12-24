/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.configuration.pojo.elements.MethodPostExecutionConfiguration;
import net.sf.oval.guard.PostCheck;

/**
 * wrapper of {@link MethodPostExecutionConfiguration}
 * 
 * @author brad.wu
 */
public class MethodPostExecutionConfigurationWrapper extends MethodPostExecutionConfiguration {
	private static final long serialVersionUID = 1L;

	/**
	 * @return the checks
	 */
	public List<PostCheck> getChecks() {
		return checks;
	}

	/**
	 * @param checks
	 *            the checks to set
	 */
	public void setChecks(List<PostCheck> checks) {
		this.checks = checks;
	}

	/**
	 * add post check
	 * 
	 * @param check
	 */
	public void addCheck(PostCheck check) {
		if (this.checks == null) {
			synchronized (this) {
				if (this.checks == null) {
					this.checks = new ArrayList<PostCheck>();
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
