/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.github.nest.arcteryx.persistent.CascadeType;
import com.github.nest.arcteryx.persistent.CollectionType;
import com.github.nest.arcteryx.persistent.ICollectionParameter;

/**
 * collection parameter
 * 
 * @author brad.wu
 */
public abstract class AbstractCollectionParameter implements ICollectionParameter {
	private static final long serialVersionUID = 3954678804953985870L;
	public static final CascadeType[] DEFAULT_CASCADE_TYPES = { CascadeType.SAVE_UPDATE, CascadeType.DELETE };

	private CollectionType type = null;
	private CascadeType[] cascadeTypes = DEFAULT_CASCADE_TYPES;
	private boolean inverse = false;
	private Map<String, Object> parameters = null;

	public AbstractCollectionParameter(CollectionType type) {
		this.type = type;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.ICollectionParameter#getType()
	 */
	@Override
	public CollectionType getType() {
		return this.type;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.ICollectionParameter#getCascadeTypes()
	 */
	@Override
	public CascadeType[] getCascadeTypes() {
		return ArrayUtils.isEmpty(this.cascadeTypes) ? DEFAULT_CASCADE_TYPES : this.cascadeTypes;
	}

	/**
	 * set as null means reset to default.
	 * 
	 * @param cascadeTypes
	 *            the cascadeTypes to set
	 */
	public void setCascadeTypes(CascadeType[] cascadeTypes) {
		this.cascadeTypes = cascadeTypes;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.ICollectionParameter#isInverse()
	 */
	@Override
	public boolean isInverse() {
		return this.inverse;
	}

	/**
	 * @param inverse
	 *            the inverse to set
	 */
	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.ICollectionParameter#getParameter(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getParameter(String key) {
		return (T) (this.parameters == null ? null : this.parameters.get(key));
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.ICollectionParameter#addParameter(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public ICollectionParameter addParameter(String key, Object value) {
		if (this.parameters == null) {
			synchronized (this) {
				if (this.parameters == null) {
					this.parameters = new HashMap<String, Object>();
				}
			}
		}
		this.parameters.put(key, value);
		return this;
	}
}
