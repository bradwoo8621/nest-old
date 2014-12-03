/**
 * 
 */
package com.github.nest.sparrow.enums.internal;

import com.github.nest.sparrow.enums.IEnumItem;

/**
 * enumeration item
 * 
 * @author brad.wu
 */
public class EnumItem implements IEnumItem {
	private static final long serialVersionUID = 8611922362526912746L;

	private String id = null;
	private String label = null;

	public EnumItem(String id, String label) {
		assert id != null && id.trim().length() != 0 : "ID cannot be null or empty string.";
		
		this.id = id;
		this.label = label;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnumItem#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnumItem#getLabel()
	 */
	@Override
	public String getLabel() {
		return this.label;
	}

	/**
	 * generate hash code by {@link #id}
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * original hash code from Object, to keep the memory address
	 * 
	 * @return
	 */
	protected int originalHashCode() {
		return super.hashCode();
	}

	/**
	 * only compare the {@link #id}.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnumItem other = (EnumItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalHashCode() + " [id=" + id + ", label=" + label + "]";
	}
}
