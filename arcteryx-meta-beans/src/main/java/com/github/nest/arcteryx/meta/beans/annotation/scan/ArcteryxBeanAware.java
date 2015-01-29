/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.github.nest.arcteryx.meta.annotation.ArcteryxResourceAware;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBean;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxCachedBean;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxSpringCachedBean;

/**
 * arcteryx bean handler
 * 
 * @author brad.wu
 */
public class ArcteryxBeanAware extends ArcteryxResourceAware {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.ArcteryxResourceAware#getConcernedAnnotations()
	 */
	@Override
	public Set<Class<? extends Annotation>> getConcernedAnnotations() {
		Set<Class<? extends Annotation>> set = new HashSet<Class<? extends Annotation>>();
		set.add(ArcteryxBean.class);
		set.add(ArcteryxCachedBean.class);
		set.add(ArcteryxSpringCachedBean.class);

		Set<Class<? extends Annotation>> annotations = super.getConcernedAnnotations();
		if (annotations != null) {
			set.addAll(annotations);
		}
		return set;
	}
}