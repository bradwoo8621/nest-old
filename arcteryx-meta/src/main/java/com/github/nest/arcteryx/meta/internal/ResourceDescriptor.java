/**
 * 
 */
package com.github.nest.arcteryx.meta.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.IResourceOperator;
import com.github.nest.arcteryx.meta.ResourceException;

/**
 * Resource descriptor. <br>
 * <b>Note</b> the properties from its ancestor's will be cached in current
 * descriptor, which means if properties of its ancestors are changed, <b>cache
 * will not be reset once the cache was initialized</b>. Since the descriptor is
 * implemented as definition information, so the changing is not considered now.
 * 
 * @author brad.wu
 */
public class ResourceDescriptor implements IResourceDescriptor {
	private static final long serialVersionUID = 7187193550596533383L;

	private String name = null;
	private Class<?> resourceClass = null;
	private String description = null;
	private IResourceDescriptorContext context = null;
	private Collection<IPropertyDescriptor> properties = null;
	private Map<String, IResourceOperator> operators = new HashMap<String, IResourceOperator>();
	private IResourceDescriptor parent = null;
	private Collection<IPropertyDescriptor> allProperties = null;

	public ResourceDescriptor() {
	}

	public ResourceDescriptor(IResourceDescriptor parent) {
		setParent(parent);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		assert StringUtils.isNotBlank(name) : "Name of resource descriptor cannot be null or empty string.";

		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getResourceClass()
	 */
	@Override
	public Class<?> getResourceClass() {
		return this.resourceClass;
	}

	/**
	 * @param resourceClass
	 *            the resourceClass to set
	 */
	public void setResourceClass(Class<?> resourceClass) {
		assert resourceClass != null : "Resource class cannot be null.";

		this.resourceClass = resourceClass;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getContext()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceDescriptorContext> T getContext() {
		return (T) this.context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(IResourceDescriptorContext context) {
		this.context = context;
	}

	/**
	 * never return null, if no properties described, return empty collection.
	 * Call {@linkplain #getProperties(boolean)} and send parameter
	 * <code>true</code>.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getProperties()
	 */
	@Override
	public Collection<IPropertyDescriptor> getProperties() {
		return this.getProperties(true);
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Collection<IPropertyDescriptor> properties) {
		this.properties = properties;

		if (properties != null) {
			for (IPropertyDescriptor property : properties) {
				property.setResourceDescriptor(this);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getOperator(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceOperator> T getOperator(String code) {
		assert StringUtils.isNotBlank(code) : "Code cannot be null or empty string.";

		IResourceOperator operator = this.operators.get(code);
		if (operator == null) {
			synchronized (this.operators) {
				operator = this.operators.get(code);
				if (operator == null) {
					// not existed or existed but is null
					if (!this.operators.containsKey(code)) {
						// initialize the operator, since key is not existed
						// either
						operator = this.getContext().getOperatorProviderRegistry().createDefaultOperator(this, code);
						this.operators.put(code, operator);
					}
				}
			}
		}

		if (operator == null) {
			throw new ResourceException("Operator[" + code + "] not found on resource[" + getName() + "].");
		}

		return (T) operator;
	}

	/**
	 * @param operators
	 *            the operators to set
	 */
	public void setOperators(Collection<IResourceOperator> operators) {
		synchronized (this.operators) {
			this.operators.clear();
			for (IResourceOperator operator : operators) {
				operator.setResourceDescriptor(this);
				this.operators.put(operator.getCode(), operator);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getOperators()
	 */
	@Override
	public Collection<IResourceOperator> getOperators() {
		return this.operators.values();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getParent()
	 */
	@Override
	public IResourceDescriptor getParent() {
		return this.parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(IResourceDescriptor parent) {
		this.parent = parent;
	}

	/**
	 * Will cache the ancestor's properties at first call when the parameter
	 * <code>all</code> is true, and never change again.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptor#getProperties(boolean)
	 */
	@Override
	public Collection<IPropertyDescriptor> getProperties(boolean all) {
		if (all) {
			if (this.allProperties == null) {
				synchronized (this) {
					if (this.allProperties == null) {
						this.allProperties = getAllProperties();
					}
				}
			}
			return this.allProperties;
		} else {
			if (this.properties == null) {
				return Collections.emptyList();
			} else {
				return this.properties;
			}
		}
	}

	/**
	 * get all properties which collect in this moment
	 * 
	 * @return
	 */
	protected Collection<IPropertyDescriptor> getAllProperties() {
		List<IPropertyDescriptor> list = new ArrayList<IPropertyDescriptor>();
		list.addAll(getProperties(false));

		IResourceDescriptor parent = this.getParent();
		if (parent != null) {
			Map<String, IPropertyDescriptor> map = new HashMap<String, IPropertyDescriptor>();
			Collection<IPropertyDescriptor> parentProperties = parent.getProperties(true);
			if (parentProperties != null) {
				for (IPropertyDescriptor descriptor : parentProperties) {
					map.put(descriptor.getName(), descriptor);
				}
			}

			// replace the property
			for (IPropertyDescriptor descriptor : list) {
				map.put(descriptor.getName(), descriptor);
			}

			return map.values();
		} else {
			return list;
		}
	}

	/**
	 * return super.toString();
	 * 
	 * @return
	 */
	protected final String originalToString() {
		return super.toString();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return originalToString() + " [name=" + name + ", resourceClass=" + resourceClass + ", description="
				+ description + ", properties=" + properties + ", operators=" + operators + ", parent=" + parent + "]";
	}
}
