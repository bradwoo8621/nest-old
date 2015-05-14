/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes;

import com.github.nest.arcteryx.data.codes.CodesRuntimeException;
import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;

/**
 * constanted code table
 * 
 * @author brad.wu
 */
public abstract class AbstractConstantedCodeTable extends AbstractCodeTable {
	private static final long serialVersionUID = 2607295226580778988L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#createContentProvider()
	 */
	@Override
	protected ICodeTableContentProvider createContentProvider() throws CodesRuntimeException {
		return new ConstantedCodeTableContentProvider(createCodes());
	}

	/**
	 * create codes
	 * 
	 * @return
	 */
	protected abstract String[] createCodes();
}
