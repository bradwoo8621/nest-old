/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import java.util.Collection;
import java.util.Iterator;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeItemFilter;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;
import com.github.nest.arcteryx.data.codes.ICodeTableFilter;

/**
 * default code table.
 * 
 * @author brad.wu
 */
public class DefaultCodeTable implements ICodeTable {
	private static final long serialVersionUID = -345828925582710125L;

	private String name = null;
	private ICodeTableContentProvider contentProvider = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String code) {
		return this.getContentProvider().contains(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#getItem(java.lang.String)
	 */
	@Override
	public ICodeItem getItem(String code) {
		return this.getContentProvider().getItem(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#iterator()
	 */
	@Override
	public Iterator<ICodeItem> iterator() {
		return this.getContentProvider().iterator();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#getItems()
	 */
	@Override
	public Collection<ICodeItem> getItems() {
		return this.getContentProvider().getItems();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#getItems(com.github.nest.arcteryx.data.codes.ICodeItemFilter)
	 */
	@Override
	public Collection<ICodeItem> getItems(ICodeItemFilter filter) {
		return this.getContentProvider().getItems(filter);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTable#getItems(com.github.nest.arcteryx.data.codes.ICodeTableFilter)
	 */
	@Override
	public Collection<ICodeItem> getItems(ICodeTableFilter filter) {
		return this.getContentProvider().getItems(filter);
	}

	/**
	 * @return the contentProvider
	 */
	public ICodeTableContentProvider getContentProvider() {
		return contentProvider;
	}

	/**
	 * @param contentProvider
	 *            the contentProvider to set
	 */
	public void setContentProvider(ICodeTableContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
}
