/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * static code resource operator. code of operator will be created by
 * {@linkplain #createCode()} and cannot be changed.
 * 
 * @author brad.wu
 */
public abstract class AbstractStaticCodeResourceOperator implements IResourceOperator {
	private String code = null;
	private IResourceDescriptor resourceDescriptor = null;

	public AbstractStaticCodeResourceOperator() {
		this.code = createCode();
	}

	/**
	 * create code
	 * 
	 * @return
	 */
	protected abstract String createCode();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#getCode()
	 */
	@Override
	public final String getCode() {
		return this.code;
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
