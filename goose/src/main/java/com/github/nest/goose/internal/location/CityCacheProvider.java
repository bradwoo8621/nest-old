/**
 * 
 */
package com.github.nest.goose.internal.location;

/**
 * city cache provider
 * 
 * @author brad.wu
 */
public class CityCacheProvider extends FileLineCacheProvider {
	/**
	 * create bean by given line
	 * 
	 * @param line
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		City city = new City();
		city.setCode(ss[0]);
		city.setName(ss[1]);
		city.setProvinceCode(ss[2]);
		city.setCountryCode(ss[3]);
		return (T) city;
	}
}
