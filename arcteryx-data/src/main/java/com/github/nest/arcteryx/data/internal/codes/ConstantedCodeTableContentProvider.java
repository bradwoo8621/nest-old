package com.github.nest.arcteryx.data.internal.codes;

import java.util.HashSet;
import java.util.Set;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeTable;

/**
 * constant content provider
 * 
 * @author brad.wu
 */
public class ConstantedCodeTableContentProvider extends DefaultCodeTableContentProvider {
	private String[] codes = null;

	public ConstantedCodeTableContentProvider(String[] codes) {
		this.codes = codes;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#initializeItems(com.github.nest.arcteryx.data.codes.ICodeTable)
	 */
	@Override
	protected Set<ICodeItem> initializeItems(ICodeTable codeTable) {
		Set<ICodeItem> items = new HashSet<ICodeItem>();
		for (String code : codes) {
			items.add(new CodeItem(code));
		}
		return items;
	}
}