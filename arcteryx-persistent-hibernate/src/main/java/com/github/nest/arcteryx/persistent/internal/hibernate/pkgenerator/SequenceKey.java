/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * sequence key generator.<br>
 * uses a sequence in DB2, PostgreSQL, Oracle, SAP DB, McKoi or a generator in
 * Interbase. The returned identifier is of type long, short or int
 * 
 * @author brad.wu
 */
public class SequenceKey implements IPrimaryKey {
	private static final long serialVersionUID = 6016064202484377256L;

	private String sequenceName = null;

	/**
	 * @return the sequenceName
	 */
	public String getSequenceName() {
		return sequenceName;
	}

	/**
	 * @param sequenceName
	 *            the sequenceName to set
	 */
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
}
