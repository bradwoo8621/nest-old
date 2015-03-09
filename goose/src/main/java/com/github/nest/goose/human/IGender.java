/**
 * 
 */
package com.github.nest.goose.human;

import java.io.Serializable;

/**
 * gender
 * 
 * @author brad.wu
 */
public interface IGender extends Serializable {
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
