/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.data.codes.ICodeItem;

/**
 * @author brad.wu
 */
public class CodeItem implements ICodeItem {
	private static final long serialVersionUID = -3004806605691358568L;

	private String code = null;

	public CodeItem(String code) {
		this.setCode(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeItem#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	protected void setCode(String code) {
		assert StringUtils.isNotBlank(code) : "Code of item cannot be null.";

		this.code = code;
	}

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
		if (obj == null || !(obj instanceof ICodeItem)) {
			return false;
		}
		return this.getCode().equals(((ICodeItem) obj).getCode());
	}

	/**
	 * original to string from object
	 * 
	 * @return
	 */
	protected String orgToString() {
		return super.toString();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.orgToString() + " [code=" + code + "]";
	}
}
