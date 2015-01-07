/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * default resource operator provider registry
 * 
 * @author brad.wu
 */
public interface IDefaultOperatorProviderRegistry {
	/**
	 * create default resource operator
	 * 
	 * @param descriptor
	 * @return
	 */
	<T extends IResourceOperator> T createDefaultOperator(IResourceDescriptor descriptor, String code);

	/**
	 * register default operator provider for given operator code
	 * 
	 * @param code
	 * @param provider
	 */
	void register(String code, IOperatorProvider provider);
}
