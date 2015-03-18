/**
 * 
 */
package com.github.nest.goose.internal.location;

import com.github.nest.goose.internal.FileLineCacheProvider;

/**
 * province cache provider
 * 
 * @author brad.wu
 */
public class ProvinceCacheProvider extends FileLineCacheProvider {
	/**
	 * create bean by given line
	 * 
	 * @param line
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		Province province = new Province();
		province.setCode(ss[0]);
		province.setName(ss[1]);
		province.setCountryCode(ss[2]);
		return (T) province;
	}
}
