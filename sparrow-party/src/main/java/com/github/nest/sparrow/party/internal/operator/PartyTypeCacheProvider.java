/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import java.util.Collection;

import com.github.nest.goose.internal.AbstractCodeBaseBeanCacheProvider;
import com.github.nest.sparrow.party.internal.codes.PartyType;
import com.google.common.collect.Lists;

/**
 * gender cache provider
 * 
 * @author brad.wu
 */
public class PartyTypeCacheProvider extends AbstractCodeBaseBeanCacheProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getBeans()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getBeans() {
		return (Collection<T>) Lists.newArrayList(PartyType.INDIVIDUAL, PartyType.ORGANIZATION);
	}
}
