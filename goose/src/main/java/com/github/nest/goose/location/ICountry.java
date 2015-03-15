/**
 * 
 */
package com.github.nest.goose.location;

import com.github.nest.goose.ICodeBaseBean;

/**
 * country
 * 
 * @author brad.wu
 */
public interface ICountry extends ICodeBaseBean {
	/**
	 * get name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get abbreviation 3-letter
	 * 
	 * @return
	 */
	String getAbbr3();

	/**
	 * get abbreviation 2-letter
	 * 
	 * @return
	 */
	String getAbbr2();

	/**
	 * get international dialing code
	 * 
	 * @return
	 */
	String getInternationalDialingCode();
}
