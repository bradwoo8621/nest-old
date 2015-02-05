/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * identity key.<br>
 * supports identity columns in DB2, MySQL, MS SQL Server, Sybase and
 * HypersonicSQL. The returned identifier is of type long, short or int.
 * 
 * @author brad.wu
 */
public class IdentityKey implements IPrimaryKey {
	private static final long serialVersionUID = 4591776098993385972L;
	public static final IPrimaryKey IDENTITY = new IdentityKey();

	private IdentityKey() {
	}
}
