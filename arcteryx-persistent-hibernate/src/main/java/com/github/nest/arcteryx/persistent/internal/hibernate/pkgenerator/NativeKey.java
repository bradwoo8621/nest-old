/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * native key.<br>
 * selects identity, sequence or hilo depending upon the capabilities of the
 * underlying database.
 * 
 * @author brad.wu
 */
public class NativeKey implements IPrimaryKey {
	private static final long serialVersionUID = -3284084146466770481L;
	public static final String DEFAULT_TABLE_NAME = "HIBERNATE_UNIQUE_KEY";
	public static final String DEFAULT_COLUMN_NAME = "NEXT_HI";
	public static final int DEFAULT_MAX_LOW_VALUE = 100;

	private String tableName = null;
	private String columnName = null;
	private int maxLowValue = DEFAULT_MAX_LOW_VALUE;
	private String sequenceName = null;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the maxLowValue
	 */
	public int getMaxLowValue() {
		return maxLowValue;
	}

	/**
	 * @param maxLowValue
	 *            the maxLowValue to set
	 */
	public void setMaxLowValue(int maxLowValue) {
		this.maxLowValue = maxLowValue;
	}

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
