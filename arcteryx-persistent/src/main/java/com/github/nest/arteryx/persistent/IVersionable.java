/**
 * 
 */
package com.github.nest.arteryx.persistent;

import java.io.Serializable;

/**
 * version
 * 
 * @author brad.wu
 */
public interface IVersionable extends Serializable {
	/**
	 * get version number
	 * 
	 * @return
	 */
	Long getVersionNumber();

	/**
	 * set version number
	 * 
	 * @param versionNumber
	 */
	void setVersionNumber(Long versionNumber);
}
