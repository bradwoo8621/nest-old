/**
 * 
 */
package com.github.nest.goose;

import java.io.Serializable;

/**
 * code base bean. code is identity of bean.
 * 
 * @author brad.wu
 */
public interface ICodeBaseBean extends Serializable {
	/**
	 * get code of bean
	 * 
	 * @return
	 */
	String getCode();
}
