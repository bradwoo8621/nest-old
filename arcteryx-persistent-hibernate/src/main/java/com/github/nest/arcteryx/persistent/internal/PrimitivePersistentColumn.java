/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.IPrimaryKeyGenerator;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;

/**
 * primitive persistent column
 * 
 * @author brad.wu
 */
public class PrimitivePersistentColumn implements IPrimitivePersistentColumn {
	private static final long serialVersionUID = -1871799444733115212L;

	private String name = null;
	private PrimitiveColumnType type = null;
	private boolean primaryKey = false;
	private IPrimaryKeyGenerator primaryKeyGenerator = null;
	private boolean version = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn#getName()
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
		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn#getType()
	 */
	@Override
	public PrimitiveColumnType getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(PrimitiveColumnType type) {
		this.type = type;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn#isPrimaryKey()
	 */
	@Override
	public boolean isPrimaryKey() {
		return this.primaryKey;
	}

	/**
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn#getPrimaryKeyGenerator()
	 */
	@Override
	public IPrimaryKeyGenerator getPrimaryKeyGenerator() {
		return this.primaryKeyGenerator;
	}

	/**
	 * @param primaryKeyGenerator
	 *            the primaryKeyGenerator to set
	 */
	public void setPrimaryKeyGenerator(IPrimaryKeyGenerator primaryKeyGenerator) {
		this.primaryKeyGenerator = primaryKeyGenerator;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn#isVersion()
	 */
	@Override
	public boolean isVersion() {
		return this.version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(boolean version) {
		this.version = version;
	}
}
