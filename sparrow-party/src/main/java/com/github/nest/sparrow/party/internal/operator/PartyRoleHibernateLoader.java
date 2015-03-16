/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import java.io.Serializable;

import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentLoader;
import com.github.nest.sparrow.party.IPartyConstants;

/**
 * party role loader
 * 
 * @author brad.wu
 */
public class PartyRoleHibernateLoader extends HibernatePersistentLoader implements IPartyConstants {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentLoader#load(java.io.Serializable)
	 */
	@Override
	public <T> T load(Serializable id) {
		return super.load(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentLoader#load(java.lang.Object,
	 *      java.io.Serializable)
	 */
	@Override
	public <T> T load(T object, Serializable id) {
		return super.load(object, id);
	}
}
