/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeItemFilter;
import com.github.nest.arcteryx.data.codes.IHierarchyCodeItem;

/**
 * hierarchy code item criteria
 * 
 * @author brad.wu
 */
public class HierarchyCodeItemFilter implements ICodeItemFilter {
	private String parentValue = null;
	private boolean matchAll = false;

	public HierarchyCodeItemFilter() {
	}

	public HierarchyCodeItemFilter(String parentValue) {
		this.setParentValue(parentValue);
	}

	/**
	 * @return the parentValue
	 */
	public String getParentValue() {
		return parentValue;
	}

	/**
	 * @param parentValue
	 *            the parentValue to set
	 */
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;

		if (StringUtils.isEmpty(parentValue)) {
			this.setMatchAll(true);
		}
	}

	/**
	 * @return the matchAll
	 */
	protected boolean isMatchAll() {
		return matchAll;
	}

	/**
	 * @param matchAll
	 *            the matchAll to set
	 */
	protected void setMatchAll(boolean matchAll) {
		this.matchAll = matchAll;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeItemFilter#accept(com.github.nest.arcteryx.data.codes.ICodeItem)
	 */
	@Override
	public boolean accept(ICodeItem item) {
		assert item instanceof IHierarchyCodeItem : "Item must be an instance of ["
				+ IHierarchyCodeItem.class.getName() + "].";

		IHierarchyCodeItem hItem = (IHierarchyCodeItem) item;
		return this.isMatchAll() || StringUtils.equals(this.getParentValue(), hItem.getParentValue());
	}
}
