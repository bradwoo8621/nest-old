/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.goose.internal.FileLineCacheProvider;
import com.github.nest.sparrow.party.internal.codes.AcademicMajor;

/**
 * academic major cache provider
 * 
 * @author brad.wu
 */
public class AcademicMajorCacheProvider extends FileLineCacheProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.internal.FileLineCacheProvider#createBean(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		AcademicMajor major = new AcademicMajor();
		major.setCode(ss[0]);
		major.setName(ss[1]);
		return (T) major;
	}
}
