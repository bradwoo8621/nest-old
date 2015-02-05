/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * uuid key.<br>
 * uses a 128-bit UUID algorithm to generate identifiers of type string that are
 * unique within a network (the IP address is used). The UUID is encoded as a
 * string of 32 hexadecimal digits in length.
 * 
 * @author brad.wu
 */
public class UuidKey implements IPrimaryKey {
	private static final long serialVersionUID = -8475690802695663993L;
	public static final IPrimaryKey UUID = new UuidKey();

	private UuidKey() {
	}
}
