/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * party hibernate persistent saver provider
 * 
 * @author brad.wu
 */
public class PartyHibernatePersistentSaverProvider extends HibernatePersistentSaverProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentSaverProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceOperator> T createDefaultOperator(IResourceDescriptor descriptor) {
		if (IPartyRole.class.isAssignableFrom(descriptor.getResourceClass())) {
			PartyRoleHibernateSaver saver = new PartyRoleHibernateSaver();
			saver.setResourceDescriptor(descriptor);
			return (T) saver;
		}
		return super.createDefaultOperator(descriptor);
	}
}
