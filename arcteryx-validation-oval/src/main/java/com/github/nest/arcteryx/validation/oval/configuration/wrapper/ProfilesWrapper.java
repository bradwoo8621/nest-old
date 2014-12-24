/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.AbstractCheck;
import net.sf.oval.AbstractCheckExclusion;

/**
 * wrapper of profiles
 * 
 * @author brad.wu
 */
public class ProfilesWrapper {
	private List<String> profiles = new ArrayList<String>();

	/**
	 * add profile
	 * 
	 * @param profile
	 */
	public void add(String profile) {
		this.profiles.add(profile);
	}

	/**
	 * set to check exclusion
	 * 
	 * @param checkExclusion
	 */
	public void setToCheckExculsion(AbstractCheckExclusion checkExclusion) {
		checkExclusion.setProfiles(this.profiles.toArray(new String[this.profiles.size()]));
	}

	/**
	 * set to check
	 * 
	 * @param check
	 */
	public void setToCheck(AbstractCheck check) {
		check.setProfiles(this.profiles.toArray(new String[this.profiles.size()]));
	}
}
