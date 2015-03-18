/**
 * 
 */
package com.github.nest.goose.location;

import com.github.nest.goose.ICodeBaseBean;

/**
 * province
 * 
 * @author brad.wu
 */
public interface IProvince extends ICodeBaseBean {
	/**
	 * get name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get country code
	 * 
	 * @return
	 */
	String getCountryCode();
}
