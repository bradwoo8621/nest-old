/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.nest.arcteryx.data.IArcteryxDataExceptionCodes;
import com.github.nest.arcteryx.data.codes.CodesRuntimeException;
import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeItemFilter;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;
import com.github.nest.arcteryx.data.codes.ICodeTableFilter;

/**
 * default code table provider.<br>
 * read code items from file in class path. eg. class of code table is
 * "a.b.ACodeTable", then the file is "a/b/ACodeTable.codetable".
 * 
 * @author brad.wu
 */
public class DefaultCodeTableContentProvider implements ICodeTableContentProvider {
	public static final char DESCRIPTION_SEPARATOR = ':';
	private ICodeTable codeTable = null;
	private Map<String, ICodeItem> itemsMap = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#initialize(com.github.nest.arcteryx.data.codes.ICodeTable)
	 */
	@Override
	public void initialize(ICodeTable codeTable) {
		this.setCodeTable(codeTable);
		this.resetItems(initializeItems(codeTable));
	}

	/**
	 * @return the codeTable
	 */
	protected ICodeTable getCodeTable() {
		return codeTable;
	}

	/**
	 * @param codeTable
	 *            the codeTable to set
	 */
	protected void setCodeTable(ICodeTable codeTable) {
		this.codeTable = codeTable;
	}

	/**
	 * initialize items
	 * 
	 * @param codeTable
	 * @return
	 */
	protected Set<ICodeItem> initializeItems(ICodeTable codeTable) {
		Set<ICodeItem> items = new HashSet<ICodeItem>();
		String fileName = getFileNameInClasspath(codeTable);
		try {
			InputStream is = codeTable.getClass().getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) {
				items.add(createCodeItem(line));
			}
			reader.close();
		} catch (Exception e) {
			throw new CodesRuntimeException(IArcteryxDataExceptionCodes.CODE_TABLE_INITIAL,
					"Failed to read code of code table[" + codeTable.getName() + "] from class path[/"
							+ fileName.replace('.', '/') + "].", e);
		}
		return items;
	}

	/**
	 * create code item
	 * 
	 * @param line
	 * @return
	 */
	protected ICodeItem createCodeItem(String line) {
		int pos = line.indexOf(DESCRIPTION_SEPARATOR);
		if (pos == -1) {
			return new CodeItem(line.trim());
		} else {
			return new CodeItem(line.substring(0, pos));
		}
	}

	/**
	 * get file name in class path
	 * 
	 * @param codeTable
	 * @return
	 */
	protected String getFileNameInClasspath(ICodeTable codeTable) {
		return codeTable.getClass().getSimpleName() + ".codetable";
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#containsKey(java.lang.String)
	 */
	@Override
	public boolean containsKey(String code) {
		return this.getItemsMap().containsKey(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#getItem(java.lang.String)
	 */
	@Override
	public ICodeItem getItem(String code) {
		return this.getItemsMap().get(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#iterator()
	 */
	@Override
	public Iterator<ICodeItem> iterator() {
		return this.getItemsMap().values().iterator();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#getItems()
	 */
	@Override
	public Collection<ICodeItem> getItems() {
		return this.getItemsMap().values();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#getItems(com.github.nest.arcteryx.data.codes.ICodeItemFilter)
	 */
	@Override
	public Collection<ICodeItem> getItems(ICodeItemFilter filter) {
		List<ICodeItem> items = new LinkedList<ICodeItem>();
		for (ICodeItem item : this.getItems()) {
			if (filter.accept(item)) {
				items.add(item);
			}
		}
		return items;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableContentProvider#getItems(com.github.nest.arcteryx.data.codes.ICodeTableFilter)
	 */
	@Override
	public Collection<ICodeItem> getItems(ICodeTableFilter filter) {
		return filter.filter(codeTable);
	}

	/**
	 * @return the itemsMap
	 */
	protected Map<String, ICodeItem> getItemsMap() {
		return this.itemsMap;
	}

	/**
	 * set items
	 * 
	 * @param items
	 */
	protected void resetItems(Set<ICodeItem> items) {
		synchronized (this) {
			this.itemsMap = new HashMap<String, ICodeItem>();
			if (items != null) {
				for (ICodeItem item : items) {
					this.itemsMap.put(item.getCode(), item);
				}
			}
		}
	}
}
