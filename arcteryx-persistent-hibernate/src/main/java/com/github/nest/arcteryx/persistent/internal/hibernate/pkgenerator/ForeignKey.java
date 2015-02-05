/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator;

import com.github.nest.arcteryx.persistent.IPrimaryKey;

/**
 * foreign key.<br>
 * uses the identifier of another associated object. It is usually used in
 * conjunction with a <one-to-one> primary key association.
 * 
 * @author brad.wu
 */
public class ForeignKey implements IPrimaryKey {
	private static final long serialVersionUID = 3943065935894852320L;

	private String property = null;

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property
	 *            the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
}
