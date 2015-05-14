/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import java.util.Map;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeTable;

/**
 * lazy content provider
 * 
 * @author brad.wu
 */
public class LazyCodeTableContentProvider extends DefaultCodeTableContentProvider {
	private boolean initialized = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#initialize(com.github.nest.arcteryx.data.codes.ICodeTable)
	 */
	@Override
	public void initialize(ICodeTable codeTable) {
		this.setCodeTable(codeTable);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#getItemsMap()
	 */
	@Override
	protected Map<String, ICodeItem> getItemsMap() {
		if (!this.initialized) {
			synchronized (this) {
				this.resetItems(this.initializeItems(this.getCodeTable()));
				this.initialized = true;
			}
		}
		return super.getItemsMap();
	}
}
