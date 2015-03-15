/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver;
import com.github.nest.sparrow.party.IPartyConstants;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * Party role hibernate saver. to save the implementation of
 * {@linkplain IPartyRole}
 * 
 * @author brad.wu
 */
public class PartyRoleHibernateSaver extends HibernatePersistentSaver implements IPartyConstants {
	private String asPartyPrefix = DEFAULT_AS_PARTY_PREFIX;

	/**
	 * @return the asPartyPrefix
	 */
	public String getAsPartyPrefix() {
		return asPartyPrefix;
	}

	/**
	 * use the prefix to concatenate simple class name as entity name to find
	 * the hibernate mapping configuration.<br>
	 * eg. <br>
	 * prefix: Standalone<br>
	 * Class: a.b.c.SomeClass<br>
	 * entity-name: Standalone.SomeClass<br>
	 * see default value {@linkplain #DEFAULT_AS_PARTY_PREFIX}
	 * 
	 * @param asPartyPrefix
	 *            the asPartyPrefix to set
	 */
	public void setAsPartyPrefix(String asPartyPrefix) {
		this.asPartyPrefix = asPartyPrefix;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#insert(java.lang.Object)
	 */
	@Override
	public void insert(Object resource) {
		String entityName = getAsPartyPrefix() + "." + resource.getClass().getSimpleName();
		// TODO CHECK PARTY UNIQUE FIRST
		// SAVE AS PARTY
		this.getCurrentSession().save(entityName, resource);
		this.getCurrentSession().evict(resource);
		// SAVE AS PARTY ROLE
		this.getCurrentSession().save(resource);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#update(java.lang.Object)
	 */
	@Override
	public void update(Object resource) {
		super.update(resource);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#save(java.lang.Object)
	 */
	@Override
	public void save(Object resource) {
		String entityName = getAsPartyPrefix() + "." + resource.getClass().getSimpleName();
		// TODO CHECK PARTY UNIQUE FIRST
		// SAVE AS PARTY
		this.getCurrentSession().save(entityName, resource);
		this.getCurrentSession().evict(resource);
		// SAVE AS PARTY ROLE
		this.getCurrentSession().save(resource);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#remove(java.lang.Object)
	 */
	@Override
	public void remove(Object resource) {
		super.remove(resource);
	}
}
