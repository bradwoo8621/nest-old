package com.github.nest.arcteryx.meta.beans;

/**
 * target which constraint applies to. default is {@linkplain #VALUES}
 * 
 * @author brad.wu
 */
public enum ConstraintApplyTo {
	KEYS, VALUES, CONTAINER;
	/**
	 * get default severity
	 * 
	 * @return
	 */
	public static ConstraintApplyTo defaultApplyTo() {
		return VALUES;
	}
}