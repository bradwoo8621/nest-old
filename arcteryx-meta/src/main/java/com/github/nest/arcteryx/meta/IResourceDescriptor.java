/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.io.Serializable;
import java.util.Collection;

/**
 * Resource interface, describe the resource features, properties,
 * relationships, etc.
 * 
 * @author brad.wu
 */
public interface IResourceDescriptor extends Serializable {
	/**
	 * get name of object
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get resource class
	 * 
	 * @return
	 */
	Class<?> getResourceClass();

	/**
	 * get description of object
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * get context of descriptor
	 * 
	 * @return
	 */
	<T extends IResourceDescriptorContext> T getContext();

	/**
	 * set context
	 * 
	 * @param context
	 */
	void setContext(IResourceDescriptorContext context);

	/**
	 * get properties which declared in current descriptor and its ancestor's
	 * 
	 * @return
	 */
	Collection<IPropertyDescriptor> getDeclaredProperties();

	/**
	 * get property by given name, only property declared in current descriptor
	 * will be returned. return null when not found.
	 * 
	 * @param name
	 * @return
	 */
	<T extends IPropertyDescriptor> T getDeclaredProperty(String name);

	/**
	 * get properties declared in current and parents. if the property is
	 * declared more than twice, ancestor's will be overwritten.
	 * 
	 * @return
	 */
	Collection<IPropertyDescriptor> getProperties();

	/**
	 * get property by given name. property declared in current descriptor or
	 * its ancestor will be returned.
	 * 
	 * @param name
	 * @return
	 */
	<T extends IPropertyDescriptor> T getProperty(String name);

	/**
	 * get operator by given code
	 * 
	 * @param code
	 * @return
	 */
	<T extends IResourceOperator> T getOperator(String code);

	/**
	 * get all operators
	 * 
	 * @return
	 */
	Collection<IResourceOperator> getOperators();

	/**
	 * get parent descriptor
	 * 
	 * @return
	 */
	IResourceDescriptor getParent();
}
