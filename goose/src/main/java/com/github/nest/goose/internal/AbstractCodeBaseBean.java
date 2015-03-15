/**
 * 
 */
package com.github.nest.goose.internal;

import com.github.nest.goose.ICodeBaseBean;

/**
 * abstract code base bean
 * 
 * @author brad.wu
 */
public abstract class AbstractCodeBaseBean implements ICodeBaseBean {
	private static final long serialVersionUID = 1693152938311483687L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.getCode().hashCode();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj.getClass() == getClass()) && ((ICodeBaseBean) obj).getCode().equals(getCode());
	}
}
