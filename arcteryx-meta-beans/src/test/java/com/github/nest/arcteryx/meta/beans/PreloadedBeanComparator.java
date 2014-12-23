/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Comparator;

/**
 * @author brad.wu
 *
 */
public class PreloadedBeanComparator implements Comparator<PreloadedBean> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(PreloadedBean o1, PreloadedBean o2) {
		return 0 - o1.getName().compareTo(o2.getName());
	}
}
