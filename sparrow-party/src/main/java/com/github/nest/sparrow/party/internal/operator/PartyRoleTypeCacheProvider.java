/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.goose.internal.FileLineCacheProvider;
import com.github.nest.sparrow.party.internal.codes.PartyRoleType;

/**
 * country cache provider
 * 
 * @author brad.wu
 */
public class PartyRoleTypeCacheProvider extends FileLineCacheProvider {
	/**
	 * (non-Javadoc)
	 * @see com.github.nest.goose.internal.FileLineCacheProvider#createBean(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createBean(String line) {
		String[] ss = line.split("[|]");
		PartyRoleType roleType = new PartyRoleType();
		roleType.setCode(ss[0]);
		roleType.setName(ss[1]);
		return (T) roleType;
	}
}
