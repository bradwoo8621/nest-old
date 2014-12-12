/**
 * 
 */
package com.github.nest.arcteryx.meta.internal;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptor;

/**
 * property descriptor
 * 
 * @author brad.wu
 */
public class PropertyDescriptor implements IPropertyDescriptor {
	private static final long serialVersionUID = -6440067628906045864L;

	private String name = null;
	private String description = null;
	private IResourceDescriptor resourceDescriptor = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IPropertyDescriptor#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		assert (name != null && name.trim().length() != 0) : "Name of property descriptor cannot be null or empty string.";
		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IPropertyDescriptor#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IPropertyDescriptor#setResourceDescriptor(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void setResourceDescriptor(IResourceDescriptor resourceDescriptor) {
		this.resourceDescriptor = resourceDescriptor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IPropertyDescriptor#getResourceDescriptor()
	 */
	@Override
	public IResourceDescriptor getResourceDescriptor() {
		return this.resourceDescriptor;
	}

	/**
	 * return super.toString();
	 * 
	 * @return
	 */
	protected final String originalToString() {
		return super.toString();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return originalToString() + " [name=" + name + ", description=" + description + "]";
	}
}
