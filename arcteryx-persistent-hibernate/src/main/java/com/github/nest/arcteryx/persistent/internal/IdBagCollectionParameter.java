/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.CollectionType;
import com.github.nest.arcteryx.persistent.IPrimaryKey;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;

/**
 * idbag collection parameter
 * 
 * @author brad.wu
 */
public class IdBagCollectionParameter extends AbstractCollectionParameter {
	private static final long serialVersionUID = 2644012529830004287L;
	public static final String COLLECTION_ID_GENERATOR = "COLLECTION_ID_GENERATOR";
	public static final String COLLECTION_ID_COLUMN_NAME = "COLLECTION_ID_COLUMN_NAME";
	public static final String COLLECTION_ID_COLUMN_TYPE = "COLLECTION_ID_COLUMN_TYPE";

	public IdBagCollectionParameter() {
		super(CollectionType.IDBAG);
	}

	/**
	 * set collection-id column name
	 * 
	 * @param name
	 */
	public void setCollectionIdColumnName(String name) {
		addParameter(COLLECTION_ID_COLUMN_NAME, name);
	}

	/**
	 * get collection-id column name
	 * 
	 * @return
	 */
	public String getCollectionIdColumnName() {
		return getParameter(COLLECTION_ID_COLUMN_NAME);
	}

	/**
	 * set collection-id column type
	 * 
	 * @param type
	 */
	public void setCollectionIdColumnType(PrimitiveColumnType type) {
		addParameter(COLLECTION_ID_COLUMN_TYPE, type);
	}

	/**
	 * get collection-id column type
	 * 
	 * @return
	 */
	public PrimitiveColumnType getCollectionIdColumnType() {
		return getParameter(COLLECTION_ID_COLUMN_TYPE);
	}

	/**
	 * set collection-id generator
	 * 
	 * @param generator
	 */
	public void setCollectionIdGenerator(IPrimaryKey generator) {
		addParameter(COLLECTION_ID_GENERATOR, generator);
	}

	/**
	 * get collection-id generator
	 * 
	 * @return
	 */
	public IPrimaryKey getCollectionIdGenerator() {
		return getParameter(COLLECTION_ID_GENERATOR);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.AbstractCollectionParameter#isInverse()
	 * @deprecated inverse is not supported by idbag
	 */
	@Override
	@Deprecated
	public boolean isInverse() {
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.internal.AbstractCollectionParameter#setInverse(boolean)
	 * @deprecated inverse is not supported by idbag
	 */
	@Override
	@Deprecated
	public void setInverse(boolean inverse) {
	}
}
