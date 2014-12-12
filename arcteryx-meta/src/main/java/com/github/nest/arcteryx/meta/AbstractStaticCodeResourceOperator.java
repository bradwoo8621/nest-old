/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * static code resource operator
 * 
 * @author brad.wu
 */
public abstract class AbstractStaticCodeResourceOperator implements IResourceOperator {
	private String code = null;

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
}
