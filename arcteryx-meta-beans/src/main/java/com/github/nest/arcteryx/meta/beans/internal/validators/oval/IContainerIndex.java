/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * key in container, to identify path of the object which in container
 * 
 * @author brad.wu
 */
public interface IContainerIndex extends Serializable {
	/**
	 * get index key
	 * 
	 * @return
	 */
	String getIndexKey();

	/**
	 * index of collection, for {@linkplain Collection} and array.
	 * 
	 * @author brad.wu
	 */
	public static interface ICollectionIndex extends IContainerIndex {
		int getIndex();
	}

	/**
	 * collection index implementation
	 * 
	 * @author brad.wu
	 */
	public static class CollectionIndex implements ICollectionIndex {
		private static final long serialVersionUID = -8415876739458946986L;
		private int index = 0;

		public CollectionIndex(int index) {
			this.index = index;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IContainerIndex.ICollectionIndex#getIndex()
		 */
		@Override
		public int getIndex() {
			return this.index;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IContainerIndex#getIndexKey()
		 */
		@Override
		public String getIndexKey() {
			return String.valueOf(getIndex());
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "CollectionIndex [index=" + index + "]";
		}
	}

	/**
	 * index of map
	 * 
	 * @author brad.wu
	 *
	 */
	public static interface IMapIndex extends IContainerIndex {
		Object getKey();
	}

	/**
	 * index of map keys, for {@linkplain Map#keySet()}
	 * 
	 * @author brad.wu
	 */
	public static interface IMapKeyIndex extends IMapIndex {
	}

	/**
	 * map key index implementation
	 * 
	 * @author brad.wu
	 */
	public static class MapKeyIndex implements IMapKeyIndex {
		private static final long serialVersionUID = 9215449748119679584L;
		private Object key = null;

		public MapKeyIndex(Object key) {
			this.key = key;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IContainerIndex.IMapIndex#getKey()
		 */
		@Override
		public Object getKey() {
			return this.key;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IContainerIndex#getIndexKey()
		 */
		@Override
		public String getIndexKey() {
			return String.valueOf(getKey());
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MapKeyIndex [key=" + key + "]";
		}
	}

	/**
	 * index of map values, for {@linkplain Map#values()}
	 * 
	 * @author brad.wu
	 */
	public static interface IMapValueIndex extends IMapIndex {

	}

	/**
	 * map value index implementation
	 * 
	 * @author brad.wu
	 */
	public static class MapValueIndex implements IMapKeyIndex {
		private static final long serialVersionUID = 9215449748119679584L;
		private Object key = null;

		public MapValueIndex(Object key) {
			this.key = key;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IContainerIndex.IMapIndex#getKey()
		 */
		@Override
		public Object getKey() {
			return this.key;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IContainerIndex#getIndexKey()
		 */
		@Override
		public String getIndexKey() {
			return String.valueOf(getKey());
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MapValueIndex [key=" + key + "]";
		}
	}
}
