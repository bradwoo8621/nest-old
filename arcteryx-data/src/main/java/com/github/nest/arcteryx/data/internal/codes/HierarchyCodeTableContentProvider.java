/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import com.github.nest.arcteryx.data.codes.ICodeItem;

/**
 * hierarchy code table content provider
 * 
 * @author brad.wu
 */
public class HierarchyCodeTableContentProvider extends DefaultCodeTableContentProvider {
	public final static String SEPARATOR = "[|]";
	private String separatorRegex = SEPARATOR;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#createCodeItem(java.lang.String)
	 */
	@Override
	protected ICodeItem createCodeItem(String line) {
		String[] values = line.split(this.getSeparatorRegex());
		return new HierarchyCodeItem(values[0], values[1]);
	}

	/**
	 * @return the separator
	 */
	public String getSeparatorRegex() {
		return this.separatorRegex;
	}

	/**
	 * @param separatorRegex
	 *            the separatorRegex to set
	 */
	public void setSeparatorRegex(String separatorRegex) {
		this.separatorRegex = separatorRegex;
	}
}
