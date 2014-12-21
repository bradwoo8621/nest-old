/**
 * 
 */
package com.github.nest.arcteryx.meta;

import org.apache.commons.lang3.StringUtils;

/**
 * abstract resource operator
 * 
 * @author brad.wu
 */
public abstract class AbstractResourceOperator implements IResourceOperator {
	private String code = null;
	private IResourceDescriptor resourceDescriptor = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		assert StringUtils.isNotBlank(code) : "Code cannot be null or empty string.";
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#setResourceDescriptor(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void setResourceDescriptor(IResourceDescriptor resourceDescriptor) {
		this.resourceDescriptor = resourceDescriptor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#getResourceDescriptor()
	 */
	@Override
	public IResourceDescriptor getResourceDescriptor() {
		return this.resourceDescriptor;
	}

	/**
	 * get resource descriptor as given class
	 * 
	 * @param descriptorClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends IResourceDescriptor> T getResourceDescriptor(Class<T> descriptorClass) {
		return (T) getResourceDescriptor();
	}
}
