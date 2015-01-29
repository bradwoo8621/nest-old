/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.annotation.AnnotationDefineException;
import com.github.nest.arcteryx.meta.beans.IBeanConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.annotation.Constraint;
import com.github.nest.arcteryx.meta.beans.annotation.ConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.annotation.Constraints;

/**
 * Constraint utilities
 * 
 * @author brad.wu
 */
public class ConstraintUtils {
	/**
	 * generate annotation to constraints
	 * 
	 * @param annotations
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static List<IConstraint> generateConstraints(Annotation[] annotations) throws Exception {
		List<IConstraint> constraints = new LinkedList<IConstraint>();
		if (annotations != null && annotations.length != 0) {
			for (Annotation annotation : annotations) {
				if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
					constraints.add(createConstraint(annotation));
				} else if (annotation.annotationType().isAnnotationPresent(Constraints.class)) {
					final Method getValue = annotation.annotationType().getDeclaredMethod("values", (Class<?>[]) null);
					final Object[] children = (Object[]) getValue.invoke(annotation, (Object[]) null);
					for (Object child : children) {
						constraints.add(createConstraint((Annotation) child));
					}
				}
			}
		}
		return constraints;
	}

	/**
	 * create constraint
	 * 
	 * @param annotation
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static IConstraint createConstraint(Annotation annotation) throws Exception {
		final Constraint constraintAnnotation = annotation.annotationType().getAnnotation(Constraint.class);
		IConstraint constraint = constraintAnnotation.constraintClass().newInstance();
		constraint.configure(annotation);
		return constraint;
	}

	/**
	 * read type level constraint reorganizer
	 * 
	 * @param beanClass
	 * @throws Exception
	 */
	public static IBeanConstraintReorganizer readTypeConstraintReogranizer(Class<?> beanClass) throws Exception {
		Annotation annotation = readTypeConstraintReorganizerAnnotation(beanClass);
		if (annotation != null) {
			ConstraintReorganizer reorganizerAnnotation = annotation.annotationType().getAnnotation(
					ConstraintReorganizer.class);
			IConstraintReorganizerGenerator generator = reorganizerAnnotation.generator().newInstance();
			IBeanConstraintReorganizer reorganizer = generator.generate(annotation);
			return reorganizer;
		}
		return null;
	}

	/**
	 * get type level constraint reorganizer annotation
	 * 
	 * @param beanClass
	 * @return
	 */
	public static Annotation readTypeConstraintReorganizerAnnotation(Class<?> beanClass) {
		Annotation reorganizer = null;
		Annotation[] annotations = beanClass.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().isAnnotationPresent(ConstraintReorganizer.class)) {
				if (reorganizer == null) {
					reorganizer = annotation;
				} else {
					throw new AnnotationDefineException("Only one constraint reorganizer annotation can be defined.");
				}
			}
		}
		return reorganizer;
	}
}
