/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;
import com.github.nest.arcteryx.data.codes.ICodeTableScanner;

/**
 * abstract code table scanner
 * 
 * @author brad.wu
 */
public abstract class AbstractCodeTableScanner implements ICodeTableScanner {
	/**
	 * create code table
	 * 
	 * @param name
	 * @param contentProvider
	 * @return
	 */
	protected ICodeTable create(String name, ICodeTableContentProvider contentProvider) {
		DefaultCodeTable codeTable = new DefaultCodeTable();
		codeTable.setName(name);
		codeTable.setContentProvider(contentProvider);
		contentProvider.initialize(codeTable);
		return codeTable;
	}

	/**
	 * register code table
	 * 
	 * @param codeTable
	 */
	protected void register(ICodeTableRegistry registry, ICodeTable codeTable) {
		registry.register(codeTable);
	}
}
