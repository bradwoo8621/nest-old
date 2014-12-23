/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.AbstractBeanOperator;
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
	public Comparator<?> getComparator() {
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
	 * @return the sortedBeans
	 */
	protected List<?> getSortedBeans() {
		return sortedBeans;
	}

	/**
	 * @param sortedBeans
	 *            the sortedBeans to set
	 */
	protected void setSortedBeans(List<?> sortedBeans) {
		this.sortedBeans = sortedBeans;
	}

	/**
	 * return {@linkplain List},
	 * <ul>
	 * <li>parameter is null, get cached sorted bean if existed,</li>
	 * <li>parameter should be a {@linkplain Collection} or array,</li>
	 * <li>otherwise return parameter itself.</li>
	 * </ul>
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T handle(Object parameter) {
		if (parameter == null && this.isCached()) {
			return (T) sort();
		}

		if (parameter instanceof Collection) {
			return (T) sort((Collection) parameter);
		} else if (parameter.getClass().isArray()) {
			List<?> list = Arrays.asList(parameter);
			return (T) sort(list);
		} else {
			// maybe will throw ClassCastException
			return (T) parameter;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanSorter#sort()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> sort() {
		if (this.isCached()) {
			return (List<T>) this.getSortedBeans();
		}

		List<T> list = null;
		Collection<T> beans = null;
		IBeanDescriptor descriptor = getResourceDescriptor();
		if (descriptor instanceof ICachedBeanDescriptor) {
			beans = ((ICachedBeanDescriptor) descriptor).getBeans();
			list = new ArrayList<T>(beans.size());
			list.addAll(beans);
			Collections.sort(list, (Comparator<T>) getComparator());
			// hold sorted beans
			if (this.isCached()) {
				this.setSortedBeans(list);
			}
		} else {
			// no beans found, return empty list
			list = Collections.emptyList();
		}
		return list;
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
