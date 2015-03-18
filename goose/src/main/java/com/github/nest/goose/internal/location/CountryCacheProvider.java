/**
 * 
 */
package com.github.nest.goose.internal.location;

import com.github.nest.goose.internal.FileLineCacheProvider;

/**
 * country cache provider
 * 
 * @author brad.wu
 */
public class CountryCacheProvider extends FileLineCacheProvider {
	/**
	 * create bean by given line
	 * 
	 * @param line
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		Country country = new Country();
		country.setAbbr2(ss[0]);
		country.setAbbr3(ss[1]);
		country.setInternationalDialingCode(ss[2]);
		country.setName(ss[3]);
		return (T) country;
	}
}
