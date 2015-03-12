/**
 * 
 */
package com.github.nest.goose.internal;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.IBeanIdentity;

/**
 * code of bean
 * 
 * @author brad.wu
 */
public final class Code implements IBeanIdentity {
	private static final long serialVersionUID = 5274567431322094984L;

	private String code = null;

	public Code() {
	}

	public Code(String code) {
		setCode(code);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (StringUtils.isEmpty(getCode()) ? 0 : getCode().hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Code another = (Code) obj;
		return StringUtils.equals(this.getCode(), another.getCode());
	}
}
