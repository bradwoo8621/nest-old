/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;

/**
 * industry
 * 
 * @author brad.wu
 */
public interface IIndustry extends Serializable {
	/**
	 * get code
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * get name
	 * 
	 * @return
	 */
	String getName();
}
