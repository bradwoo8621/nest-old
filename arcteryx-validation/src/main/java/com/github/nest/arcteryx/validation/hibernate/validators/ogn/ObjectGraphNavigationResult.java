/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.ogn;

import java.lang.reflect.AccessibleObject;

/**
 * object graph navigation result, copy from OVal
 * 
 * @author brad.wu
 */
public class ObjectGraphNavigationResult {
	private Object root;

	private String path;

	private Object targetParent;

	/**
	 * field or method
	 */
	private AccessibleObject targetAccessor;

	/**
	 * accessor's value
	 */
	private Object target;

	public ObjectGraphNavigationResult(final Object root, final String path, final Object targetParent,
			final AccessibleObject targetAccessor, final Object target) {
		this.root = root;
		this.path = path;
		this.targetParent = targetParent;
		this.targetAccessor = targetAccessor;
		this.target = target;
	}

	/**
	 * @return the root
	 */
	public Object getRoot() {
		return root;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the targetParent
	 */
	public Object getTargetParent() {
		return targetParent;
	}

	/**
	 * @return the targetAccessor
	 */
	public AccessibleObject getTargetAccessor() {
		return targetAccessor;
	}

	/**
	 * @return the target
	 */
	public Object getTarget() {
		return target;
	}
}
