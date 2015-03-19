/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.goose.internal.FileLineCacheProvider;
import com.github.nest.sparrow.party.internal.codes.JobTitle;

/**
 * job title cache provider
 * 
 * @author brad.wu
 */
public class JobTitleCacheProvider extends FileLineCacheProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.internal.FileLineCacheProvider#createBean(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		JobTitle jobTitle = new JobTitle();
		jobTitle.setCode(ss[0]);
		jobTitle.setName(ss[1]);
		return (T) jobTitle;
	}
}
