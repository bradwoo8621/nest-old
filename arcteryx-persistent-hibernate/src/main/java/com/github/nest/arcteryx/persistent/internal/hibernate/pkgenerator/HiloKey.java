/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * Hilo key generator.<br>
 * uses a hi/lo algorithm to efficiently generate identifiers of type long,
 * short or int, given a table and column (by default <b>hibernate_unique_key</b> and
 * <b>next_hi</b> respectively) as a source of hi values. The hi/lo algorithm generates
 * identifiers that are unique only for a particular database.
 * 
 * @author brad.wu
 */
public class HiloKey implements IPrimaryKey {
	private static final long serialVersionUID = -1585244818491277411L;
	public static final String DEFAULT_TABLE_NAME = "HIBERNATE_UNIQUE_KEY";
	public static final String DEFAULT_COLUMN_NAME = "NEXT_HI";
	public static final int DEFAULT_MAX_LOW_VALUE = 100;

	private String tableName = null;
	private String columnName = null;
	private int maxLowValue = DEFAULT_MAX_LOW_VALUE;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return StringUtils.isBlank(tableName) ? DEFAULT_TABLE_NAME : tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the column
	 */
	public String getColumnName() {
		return StringUtils.isBlank(columnName) ? DEFAULT_COLUMN_NAME : columnName;
	}

	/**
	 * @param columnName
	 *            the column to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the maxLowValue
	 */
	public int getMaxLowValue() {
		return maxLowValue <= 0 ? DEFAULT_MAX_LOW_VALUE : maxLowValue;
	}

	/**
	 * @param maxLowValue
	 *            the maxLowValue to set
	 */
	public void setMaxLowValue(int maxLowValue) {
		this.maxLowValue = maxLowValue;
	}
}
