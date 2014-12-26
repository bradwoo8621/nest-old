/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.util;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public final class ThreadLocalObjectCache<K, V> extends ThreadLocal<ObjectCache<K, V>> {
	private final int maxElementsToKeep;

	public ThreadLocalObjectCache() {
		this.maxElementsToKeep = -1;
	}

	public ThreadLocalObjectCache(final int maxElementsToKeep) {
		this.maxElementsToKeep = maxElementsToKeep;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectCache<K, V> initialValue() {
		return new ObjectCache<K, V>(maxElementsToKeep);
	}
}
