/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * assigned key.<br>
 * lets the application assign an identifier to the object before save() is
 * called. This is the default strategy if no <generator> element is specified.
 * 
 * @author brad.wu
 */
public class AssignedKey implements IPrimaryKey {
	private static final long serialVersionUID = -3094775056836018443L;
	public final static IPrimaryKey ASSIGNED = new AssignedKey();

	private AssignedKey() {
	}
}
