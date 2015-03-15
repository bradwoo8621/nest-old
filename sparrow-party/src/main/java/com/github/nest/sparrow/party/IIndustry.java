/**
 * 
 */
package com.github.nest.sparrow.party;

import com.github.nest.goose.ICodeBaseBean;

/**
 * industry
 * 
 * @author brad.wu
 */
public interface IIndustry extends ICodeBaseBean {
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
