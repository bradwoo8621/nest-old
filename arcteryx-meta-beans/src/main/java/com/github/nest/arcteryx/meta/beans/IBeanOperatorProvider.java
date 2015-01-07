/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * bean operator provider
 * 
 * @author brad.wu
 */
public interface IBeanOperatorProvider {
	/**
	 * create bean destroyer
	 * 
	 * @param descriptor
	 * @return
	 */
	IBeanDestroyer createDefaultBeanDestoryer(IBeanDescriptor descriptor);

	/**
	 * create bean creator
	 * 
	 * @param descriptor
	 * @return
	 */
	IBeanCreator createDefaultBeanCreator(IBeanDescriptor descriptor);

	/**
	 * create bean validator
	 * 
	 * @param descriptor
	 * @return
	 */
	IBeanValidator createDefaultBeanValidator(IBeanDescriptor descriptor);
}
