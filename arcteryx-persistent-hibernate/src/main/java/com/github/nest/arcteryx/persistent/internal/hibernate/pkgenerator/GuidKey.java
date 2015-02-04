/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * guid key.<br>
 * uses a database-generated GUID string on MS SQL Server and MySQL.
 * 
 * @author brad.wu
 */
public class GuidKey implements IPrimaryKey {
	private static final long serialVersionUID = -3522466889900540507L;
}
