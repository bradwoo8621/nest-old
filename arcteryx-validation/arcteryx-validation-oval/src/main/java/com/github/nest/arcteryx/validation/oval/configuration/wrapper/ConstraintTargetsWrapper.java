/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.AbstractCheck;
import net.sf.oval.ConstraintTarget;

/**
 * wrapper of constraint target
 * 
 * @author brad.wu
 */
public class ConstraintTargetsWrapper {
	private List<String> targets = new ArrayList<String>();

	/**
	 * add target
	 * 
	 * @param target
	 */
	public void add(String target) {
		this.targets.add(target);
	}

	/**
	 * set to check
	 * 
	 * @param check
	 */
	public void setToCheck(AbstractCheck check) {
		if (this.targets.size() > 0) {
			List<ConstraintTarget> list = new ArrayList<ConstraintTarget>();
			for (String target : targets) {
				list.add(ConstraintTarget.valueOf(target));
			}
			check.setAppliesTo(list.toArray(new ConstraintTarget[list.size()]));
		}
	}
}
