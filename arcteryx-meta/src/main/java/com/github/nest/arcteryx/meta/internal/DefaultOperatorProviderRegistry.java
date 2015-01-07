/**
 * 
 */
package com.github.nest.arcteryx.meta.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IDefaultOperatorProviderRegistry;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * default resource operator provider registry
 * 
 * @author brad.wu
 */
public class DefaultOperatorProviderRegistry implements IDefaultOperatorProviderRegistry {
	private Map<String, IOperatorProvider> providerMap = new HashMap<String, IOperatorProvider>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IDefaultOperatorProviderRegistry#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceOperator> T createDefaultOperator(IResourceDescriptor descriptor, String code) {
		IOperatorProvider provider = providerMap.get(code);
		return (T) (provider == null ? null : provider.createDefaultOperator(descriptor));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IDefaultOperatorProviderRegistry#register(java.lang.String,
	 *      com.github.nest.arcteryx.meta.IOperatorProvider)
	 */
	@Override
	public void register(String code, IOperatorProvider provider) {
		assert StringUtils.isNotBlank(code) : "Code of operator provider cannot be empty.";
		assert provider != null : "Operator provider cannot be null.";
		
		providerMap.put(code, provider);
	}
}
