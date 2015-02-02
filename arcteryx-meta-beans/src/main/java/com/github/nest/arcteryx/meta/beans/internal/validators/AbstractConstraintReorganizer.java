/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.IConstraintContainer;
import com.github.nest.arcteryx.meta.beans.IConstraintReorganizer;

/**
 * abstract constraint reorganizer
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractConstraintReorganizer<CC extends IConstraintContainer, C extends IConstraint> implements
		IConstraintReorganizer<CC, C> {
	private static final long serialVersionUID = -9007456508356074065L;
	private Map<String, Object> names = null;
	private Map<Class<? extends C>, Object> types = null;
	private boolean overwrite = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanConstraintReorganizer#getEffectiveConstraints(com.github.nest.arcteryx.meta.beans.IBeanDescriptor)
	 */
	@Override
	public List<C> getEffectiveConstraints(CC descriptor) {
		List<C> list = getAllConstraints(descriptor);

		// check the reorganize settings
		for (int index = list.size() - 1; index >= 0; index--) {
			C constraint = list.get(index);
			if (!accept(constraint)) {
				list.remove(index);
			}
		}

		return list;
	}

	/**
	 * is constraint skipped
	 * 
	 * @param constraint
	 * @return
	 */
	public boolean accept(C constraint) {
		return this.accept(constraint.getName()) && this.acceptType(constraint.getClass());
	}

	/**
	 * get all constraints
	 * 
	 * @param descriptor
	 * @param list
	 */
	protected abstract List<C> getAllConstraints(CC descriptor);

	/**
	 * @return the overwrite
	 */
	public boolean isOverwrite() {
		return overwrite;
	}

	/**
	 * skip constraints which defined in ancestors when {@linkplain #overwrite}
	 * is true, default value is false.
	 * 
	 * @param overwrite
	 *            the overwrite to set
	 */
	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	/**
	 * set skipped constraint names
	 * 
	 * @param names
	 */
	public void setSkippedConstraintNames(List<String> names) {
		if (names == null || names.size() == 0) {
			this.names = null;
		} else {
			if (this.names == null) {
				synchronized (this) {
					if (this.names == null) {
						this.names = new HashMap<String, Object>();
					}
				}
			} else {
				this.names.clear();
			}
			for (String name : names) {
				this.names.put(name, null);
			}
		}
	}

	/**
	 * is skipped by given constraint name or not
	 * 
	 * @param constraintName
	 * @return
	 */
	public boolean accept(String constraintName) {
		return this.names == null || !this.names.containsKey(constraintName);
	}

	/**
	 * set skipped constraint type
	 * 
	 * @param types
	 */
	public void setSkippedConstraintTypes(List<Class<? extends C>> types) {
		if (types == null || types.size() == 0) {
			this.types = null;
		} else {
			if (this.types == null) {
				synchronized (this) {
					if (this.types == null) {
						this.types = new HashMap<Class<? extends C>, Object>();
					}
				}
			} else {
				this.types.clear();
			}
			for (Class<? extends C> type : types) {
				this.types.put(type, null);
			}
		}
	}

	/**
	 * is skipped by given constraint type or not
	 * 
	 * @param constraintType
	 * @return
	 */
	public boolean acceptType(Class<?> constraintType) {
		return this.types == null || !this.types.containsKey(constraintType);
	}
}
