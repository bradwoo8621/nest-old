/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanSorter;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * bean sorter
 * 
 * @author brad.wu
 */
public class BeanSorter extends AbstractBeanOperator implements IBeanSorter {
	private boolean cached = false;
	private Comparator<?> comparator = null;
	private List<?> sortedBeans = null;

	private boolean beansCached = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanSorter#isCached()
	 */
	@Override
	public boolean isCached() {
		return this.cached;
	}

	/**
	 * @param cached
	 *            the cached to set
	 */
	public void setCached(boolean cached) {
		this.cached = cached;
	}

	/**
	 * @return the comparator
	 */
	@SuppressWarnings("rawtypes")
	public Comparator getComparator() {
		return comparator;
	}

	/**
	 * @param comparator
	 *            the comparator to set
	 */
	public void setComparator(Comparator<?> comparator) {
		this.comparator = comparator;
	}

	/**
	 * true if sorted beans are cached.
	 * 
	 * @return the beansCached
	 */
	protected boolean isBeansCached() {
		return beansCached;
	}

	/**
	 * set as true, if the sorted beans are cached.
	 * 
	 * @param beansCached
	 *            the beansCached to set
	 */
	protected void setBeansCached(boolean beansCached) {
		this.beansCached = beansCached;
	}

	/**
	 * if descriptor is {@linkplain ICachedBeanDescriptor}, will get beans from
	 * descriptor, and sort. Otherwise, return empty list.
	 * 
	 * @return the sortedBeans
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<?> getSortedBeans() {
		if (this.isBeansCached()) {
			// set as cached and beans already be cached
			return this.sortedBeans;
		}

		List list = null;
		IBeanDescriptor descriptor = getResourceDescriptor();
		if (descriptor instanceof ICachedBeanDescriptor) {
			Collection beans = ((ICachedBeanDescriptor) descriptor).getBeans();
			list = new ArrayList(beans.size());
			list.addAll(beans);
			Collections.sort(list, getComparator());
		} else {
			// no beans found, return empty list
			list = Collections.emptyList();
		}
		// hold sorted beans
		if (this.isCached()) {
			this.setSortedBeans(list);
			this.setBeansCached(true);
		}
		return list;
	}

	/**
	 * @param sortedBeans
	 *            the sortedBeans to set
	 */
	protected void setSortedBeans(List<?> sortedBeans) {
		this.sortedBeans = sortedBeans;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanSorter#sort()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> sort() {
		return (List<T>) this.getSortedBeans();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanSorter#sort(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> sort(Collection<T> beans) {
		List<T> list = new ArrayList<T>(beans.size());
		list.addAll(beans);
		Collections.sort(list, (Comparator<T>) getComparator());
		return list;
	}
}
