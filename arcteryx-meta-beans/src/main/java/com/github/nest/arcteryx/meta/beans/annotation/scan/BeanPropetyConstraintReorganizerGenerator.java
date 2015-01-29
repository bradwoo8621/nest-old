/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.annotation.AnnotationDefineException;
import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.annotation.Constraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanPropertyConstraintReorganizer;

/**
 * bean property constraint reorganizer
 * 
 * @author brad.wu
 */
public class BeanPropetyConstraintReorganizerGenerator implements IConstraintReorganizerGenerator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IAnnotationGenerator#generate(java.lang.annotation.Annotation)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> T generate(Annotation annotation) {
		com.github.nest.arcteryx.meta.beans.annotation.BeanPropertyConstraintReorganizer reorganizerAnnotation = (com.github.nest.arcteryx.meta.beans.annotation.BeanPropertyConstraintReorganizer) annotation;
		BeanPropertyConstraintReorganizer reorganizer = new BeanPropertyConstraintReorganizer();
		String[] names = reorganizerAnnotation.names();
		if (names != null) {
			reorganizer.setSkippedConstraintNames(Arrays.asList(names));
		}
		Class<?>[] types = reorganizerAnnotation.types();
		if (types != null) {
			List<Class<? extends IBeanPropertyConstraint>> list = new LinkedList<Class<? extends IBeanPropertyConstraint>>();
			for (Class<?> type : types) {
				if (IBeanConstraint.class.isAssignableFrom(type)) {
					list.add((Class<? extends IBeanPropertyConstraint>) type);
				} else if (type.isAnnotation()) {
					list.add((Class<? extends IBeanPropertyConstraint>) type.getAnnotation(Constraint.class)
							.constraintClass());
				} else {
					throw new AnnotationDefineException("Type [" + type
							+ "] of BeanPropertyConstraintReorganizer is not accepted.");
				}
			}
			reorganizer.setSkippedConstraintTypes(list);
		}
		reorganizer.setOverwrite(reorganizerAnnotation.overwrite());
		return (T) reorganizer;
	}
}
