/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.ogn;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.validation.ValidationException;

/**
 * default graph navigator implementation
 * 
 * @author brad.wu
 */
public class ObjectGraphNavigatorDefaultImpl extends AbstractObjectGraphNavigator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.ogn.IObjectGraphNavigator#navigateTo(java.lang.Object,
	 *      java.lang.String)
	 */
	public ObjectGraphNavigationResult navigateTo(final Object root, final String path) {
		assert root != null : "Root object cannot be null.";
		assert path != null : "Path cannot be null.";

		Object parent = null;
		Object target = root;
		AccessibleObject targetAccessor = null;
		for (final String chunk : path.split("\\.")) {
			parent = target;
			if (parent == null)
				return null;
			final Field field = getFieldRecursive(parent.getClass(), chunk);
			if (field == null) {
				final Method getter = getGetterRecursive(parent.getClass(), chunk);
				if (getter == null)
					throw new ValidationException("Invalid object navigation path from root object class ["
							+ root.getClass().getName() + "] path: " + path);
				targetAccessor = getter;
				target = invokeMethod(getter, parent);
			} else {
				targetAccessor = field;
				target = getFieldValue(field, parent);
			}
		}
		return new ObjectGraphNavigationResult(root, path, parent, targetAccessor, target);
	}
}
