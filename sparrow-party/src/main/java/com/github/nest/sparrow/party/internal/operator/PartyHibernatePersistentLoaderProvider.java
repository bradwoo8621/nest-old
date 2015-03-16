/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;
import com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentLoaderProvider;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * party hibernate persistent loader provider
 * 
 * @author brad.wu
 */
public class PartyHibernatePersistentLoaderProvider extends HibernatePersistentLoaderProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.providers.HibernatePersistentLoaderProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceOperator> T createDefaultOperator(IResourceDescriptor descriptor) {
		if (IPartyRole.class.isAssignableFrom(descriptor.getResourceClass())) {
			PartyRoleHibernateLoader loader = new PartyRoleHibernateLoader();
			loader.setResourceDescriptor(descriptor);
			return (T) loader;
		}
		return super.createDefaultOperator(descriptor);
	}
}
