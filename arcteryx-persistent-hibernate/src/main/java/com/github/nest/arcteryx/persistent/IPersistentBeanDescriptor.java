/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.util.Collection;
import java.util.Set;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;

/**
 * persistent bean descriptor
 * 
 * @author brad.wu
 */
public interface IPersistentBeanDescriptor extends IBeanDescriptor {
	/**
	 * get persistent properties, including ancestor's.
	 * 
	 * @return
	 */
	Collection<IPersistentBeanPropertyDescriptor> getPersistentProperties();

	/**
	 * get declared persistent properties, only in itself.
	 * 
	 * @return
	 */
	Collection<IPersistentBeanPropertyDescriptor> getDeclaredPersistentProperties();

	/**
	 * get abandoned properties. properties in its ancestors can be abandoned.
	 * 
	 * @return
	 */
	Set<String> getAbandonedProperties();

	/**
	 * get persistent bean saver
	 * 
	 * @return
	 */
	IPersistentBeanSaver getSaver();

	/**
	 * get persistent bean loader
	 * 
	 * @return
	 */
	IPersistentBeanLoader getLoader();
}
