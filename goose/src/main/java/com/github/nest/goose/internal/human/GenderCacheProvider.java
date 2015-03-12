/**
 * 
 */
package com.github.nest.goose.internal.human;

import java.util.Collection;

import com.github.nest.goose.internal.AbstractCodeBaseBeanCacheProvider;
import com.google.common.collect.Lists;

/**
 * gender cache provider
 * 
 * @author brad.wu
 */
public class GenderCacheProvider extends AbstractCodeBaseBeanCacheProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getBeans()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getBeans() {
		return (Collection<T>) Lists.newArrayList(Gender.MALE, Gender.FEMALE);
	}
}
