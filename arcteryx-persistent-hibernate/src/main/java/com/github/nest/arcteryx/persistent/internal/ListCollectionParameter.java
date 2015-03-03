/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.CollectionType;

/**
 * list collection parameter
 * 
 * @author brad.wu
 */
public class ListCollectionParameter extends AbstractCollectionParameter {
	private static final long serialVersionUID = -4270627871370145923L;
	public static final String LIST_INDEX_COLUMN_NAME = "LIST_INDEX_COLUMN_NAME";

	public ListCollectionParameter() {
		super(CollectionType.LIST);
	}

	/**
	 * set list-index column name
	 * 
	 * @param name
	 */
	public void setListIndexColumnName(String name) {
		addParameter(LIST_INDEX_COLUMN_NAME, name);
	}

	/**
	 * get list-index column name
	 * 
	 * @return
	 */
	public String getListIndexColumnName() {
		return getParameter(LIST_INDEX_COLUMN_NAME);
	}
}
