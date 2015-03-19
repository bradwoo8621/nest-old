/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.goose.internal.FileLineCacheProvider;
import com.github.nest.sparrow.party.internal.codes.Occupation;

/**
 * occupation cache provider
 * 
 * @author brad.wu
 */
public class OccupationCacheProvider extends FileLineCacheProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.internal.FileLineCacheProvider#createBean(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		Occupation occupation = new Occupation();
		occupation.setCode(ss[0]);
		occupation.setName(ss[1]);
		return (T) occupation;
	}
}
