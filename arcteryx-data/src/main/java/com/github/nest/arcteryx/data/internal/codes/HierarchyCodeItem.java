/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.data.codes.IHierarchyCodeItem;

/**
 * hierarchy code item, which has a parent property.
 * 
 * @author brad.wu
 */
public class HierarchyCodeItem extends CodeItem implements IHierarchyCodeItem {
	private static final long serialVersionUID = -2864424888574313055L;
	private String parentValue = null;

	public HierarchyCodeItem(String code, String parent) {
		super(code);
		this.setParentValue(parent);
	}

	/**
	 * @return the parent
	 */
	@Override
	public String getParentValue() {
		return parentValue;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	protected void setParentValue(String parent) {
		assert StringUtils.isNotBlank(parent) : "Parent value cannot be blank.";
		this.parentValue = parent;
	}
}
