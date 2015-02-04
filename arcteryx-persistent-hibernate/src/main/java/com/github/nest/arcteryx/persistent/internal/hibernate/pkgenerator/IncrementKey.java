/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * increment key.<br>
 * generates identifiers of type long, short or int that are unique only when no
 * other process is inserting data into the same table. <b>Do not use in a cluster</b>.
 * 
 * @author brad.wu
 */
public class IncrementKey implements IPrimaryKey {
	private static final long serialVersionUID = 9059167945581962701L;
}
