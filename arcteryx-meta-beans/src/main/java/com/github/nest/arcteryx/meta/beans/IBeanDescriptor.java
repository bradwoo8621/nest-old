/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;

import com.github.nest.arcteryx.meta.IResourceDescriptor;

/**
 * Bean descriptor
 * 
 * @author brad.wu
 */
public interface IBeanDescriptor extends IResourceDescriptor {
	/**
	 * get bean class
	 * 
	 * @return
	 */
	Class<?> getBeanClass();

	/**
	 * get constraint
	 * 
	 * @return
	 */
	IBeanConstraint getConstraint();

	/**
	 * get validator
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	IBeanValidator getValidator();

	/**
	 * get creator
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	IBeanCreator getCreator();

	/**
	 * get destroyer
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	IBeanDestroyer getDestoryer();

	/**
	 * get finder
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	IBeanFinder getFinder();

	/**
	 * get bean properties
	 * 
	 * @return
	 */
	Collection<IBeanPropertyDescriptor> getBeanProperties();
}
