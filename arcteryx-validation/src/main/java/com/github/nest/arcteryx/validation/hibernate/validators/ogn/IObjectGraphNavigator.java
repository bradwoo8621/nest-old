/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.ogn;

/**
 * object graph navigator, copy from OVal
 * 
 * @author brad.wu
 */
public interface IObjectGraphNavigator {
	/**
	 * Navigates through the object graph starting at <code>root</code> object.
	 * 
	 * @param root
	 *            the root object to start the navigation from
	 * @param path
	 *            the object navigation path relative to the root object. The
	 *            path format is implementation specific.
	 * @return the result of the navigation operation. <code>null</code> is
	 *         returned if the target could not be determined, e.g. because of
	 *         null values in the path.
	 */
	ObjectGraphNavigationResult navigateTo(final Object root, final String path) ;
}
