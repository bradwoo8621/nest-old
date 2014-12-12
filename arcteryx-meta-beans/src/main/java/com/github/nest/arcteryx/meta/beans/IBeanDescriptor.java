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
	IBeanValidator getValidator();

	/**
	 * get creator
	 * 
	 * @return
	 */
	IBeanCreator getCreator();

	/**
	 * get destroyer
	 * 
	 * @return
	 */
	IBeanDestroyer getDestoryer();

	/**
	 * get finder
	 * 
	 * @return
	 */
	IBeanFinder getFinder();

	/**
	 * get bean properties
	 * 
	 * @return
	 */
	Collection<IBeanPropertyDescriptor> getBeanProperties();
}
