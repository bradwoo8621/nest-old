/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;

/**
 * code table registry.
 * 
 * @author brad.wu
 */
public class CodeTableRegistry implements ICodeTableRegistry {
	private Map<String, ICodeTable> codeTableMap = new HashMap<String, ICodeTable>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableRegistry#getCodeTable(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends ICodeTable> T getCodeTable(String codeTableName) {
		return (T) this.codeTableMap.get(codeTableName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableRegistry#all()
	 */
	@Override
	public Collection<ICodeTable> all() {
		return this.codeTableMap.values();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableRegistry#register(com.github.nest.arcteryx.data.codes.ICodeTable)
	 */
	@Override
	public void register(ICodeTable codeTable) {
		this.codeTableMap.put(codeTable.getName(), codeTable);
		codeTable.setRegistry(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableRegistry#deregister(com.github.nest.arcteryx.data.codes.ICodeTable)
	 */
	@Override
	public void deregister(ICodeTable codeTable) {
		this.codeTableMap.remove(codeTable.getName());
		codeTable.setRegistry(null);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.codes.ICodeTableRegistry#deregister(java.lang.String)
	 */
	@Override
	public void deregister(String codeTableName) {
		ICodeTable codeTable = this.codeTableMap.remove(codeTableName);
		if (codeTable != null) {
			codeTable.setRegistry(null);
		}
	}
}
