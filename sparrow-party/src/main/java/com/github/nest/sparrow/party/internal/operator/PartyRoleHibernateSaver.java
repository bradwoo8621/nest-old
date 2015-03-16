/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver;
import com.github.nest.sparrow.party.IParty;
import com.github.nest.sparrow.party.IPartyConstants;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * Party role hibernate saver. to save the implementation of
 * {@linkplain IPartyRole}
 * 
 * @author brad.wu
 */
public class PartyRoleHibernateSaver extends HibernatePersistentSaver implements IPartyConstants {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#insert(java.lang.Object)
	 */
	@Override
	public void insert(Object resource) {
		IPartyRole partyRole = (IPartyRole) resource;
		IParty party = partyRole.getParty();

		IPersistentBeanSaver saver = this.getResourceDescriptor().getContext().get(party)
				.getOperator(IPersistentBeanSaver.CODE);
		saver.insert(party);

		this.getCurrentSession().save(partyRole);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#update(java.lang.Object)
	 */
	@Override
	public void update(Object resource) {
		IPartyRole partyRole = (IPartyRole) resource;
		IParty party = partyRole.getParty();

		IPersistentBeanSaver saver = this.getResourceDescriptor().getContext().get(party)
				.getOperator(IPersistentBeanSaver.CODE);
		saver.update(party);

		this.getCurrentSession().update(partyRole);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.hibernate.HibernatePersistentSaver#save(java.lang.Object)
	 */
	@Override
	public void save(Object resource) {
		IPartyRole partyRole = (IPartyRole) resource;
		IParty party = partyRole.getParty();

		IPersistentBeanSaver saver = this.getResourceDescriptor().getContext().get(party)
				.getOperator(IPersistentBeanSaver.CODE);
		saver.save(party);

		this.getCurrentSession().save(partyRole);
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
