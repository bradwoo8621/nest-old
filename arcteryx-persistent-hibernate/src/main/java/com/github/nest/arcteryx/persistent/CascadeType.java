/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * cascade type
 * 
 * @author brad.wu
 */
public enum CascadeType {
	/**
	 * Includes all types listed here.
	 */
	ALL("all"),
	/**
	 * Cascades save, delete, update, evict, lock, replicate, merge, persist +
	 * delete orphans
	 */
	ALL_DELETE_ORPHAN("all-delete-orphan"),
	/**
	 * Corresponds to {@link javax.persistence.CascadeType#REFRESH}.
	 */
	REFRESH("refresh"),
	/**
	 * Corresponds to the Hibernate native DELETE action.
	 */
	DELETE("delete"),
	/**
	 * Corresponds to the Hibernate native SAVE_UPDATE (direct reattachment)
	 * action.
	 */
	SAVE_UPDATE("save-update"),
	/**
	 * Corresponds to the Hibernate native REPLICATE action.
	 */
	REPLICATE("replicate"),
	/**
	 * Hibernate originally handled orphan removal as a specialized cascade.
	 */
	DELETE_ORPHAN("delete-orphan"),
	/**
	 * Corresponds to the Hibernate native LOCK action.
	 */
	LOCK("lock"),
	/**
	 * Corresponds to {@link javax.persistence.CascadeType#REFRESH}.
	 */
	DETACH("evict"),
	/**
	 * no cascade options
	 */
	NONE("none");

	private String name = null;

	private CascadeType(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
