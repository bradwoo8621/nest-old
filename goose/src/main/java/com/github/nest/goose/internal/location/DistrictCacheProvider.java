/**
 * 
 */
package com.github.nest.goose.internal.location;

import com.github.nest.goose.internal.FileLineCacheProvider;

/**
 * district cache provider
 * 
 * @author brad.wu
 */
public class DistrictCacheProvider extends FileLineCacheProvider {
	/**
	 * create bean by given line
	 * 
	 * @param line
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		District district = new District();
		district.setCode(ss[0]);
		district.setName(ss[1]);
		district.setCityCode(ss[2]);
		district.setProvinceCode(ss[3]);
		district.setCountryCode(ss[4]);
		return (T) district;
	}
}
