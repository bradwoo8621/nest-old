/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * abstract resource operator
 * 
 * @author brad.wu
 */
public abstract class AbstractResourceOperator<In, Out> implements IResourceOperator<In, Out> {
	private String code = null;

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
		this.code = code;
	}
}
