/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.goose.internal.FileLineCacheProvider;
import com.github.nest.sparrow.party.internal.codes.EducationDegree;

/**
 * education degree cache provider
 * 
 * @author brad.wu
 */
public class EducationDegreeCacheProvider extends FileLineCacheProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.internal.FileLineCacheProvider#createBean(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		EducationDegree degree = new EducationDegree();
		degree.setCode(ss[0]);
		degree.setName(ss[1]);
		return (T) degree;
	}
}
