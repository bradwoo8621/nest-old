/**
 * 
 */
package com.github.nest.arcteryx.meta.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * annotation
 * 
 * @author brad.wu
 */
public class AnnotationUtils {
	/**
	 * get annotations by given fields and methods
	 * 
	 * @param fields
	 * @param methods
	 * @return
	 */
	public static Annotation[] getAnnotations(Set<Field> fields, Set<Method> methods) {
		List<Annotation> annotations = new LinkedList<Annotation>();
		if (fields != null) {
			for (Field field : fields) {
				Annotation[] annos = field.getAnnotations();
				if (annos != null) {
					for (Annotation annotation : annos) {
						annotations.add(annotation);
					}
				}
			}
		}
		if (methods != null) {
			for (Method method : methods) {
				Annotation[] annos = method.getAnnotations();
				if (annos != null) {
					for (Annotation annotation : annos) {
						annotations.add(annotation);
					}
				}
			}
		}
		return annotations.toArray(new Annotation[annotations.size()]);
	}

	/**
	 * filter annotations by given class. annotation need to equals or presented
	 * by given annotation class.
	 * 
	 * @param annotations
	 * @param annotationClass
	 * @return
	 */
	public static Annotation[] filterAnnotations(Annotation[] annotations, Class<? extends Annotation> annotationClass) {
		List<Annotation> list = new LinkedList<Annotation>();
		if (annotations != null) {
			for (Annotation annotation : annotations) {
				Class<?> annotationType = annotation.annotationType();
				if (annotationType == annotationClass) {
					list.add(annotation);
				} else if (annotationType.isAnnotationPresent(annotationClass)) {
					list.add(annotation);
				}
			}
		}
		return list.toArray(new Annotation[list.size()]);
	}

	/**
	 * get annotations by given fields and methods, and filter by given
	 * annotationClass. annotation need to equals or presented by given
	 * annotation class.
	 * 
	 * @param fields
	 * @param methods
	 * @param annotationClass
	 * @return
	 */
	public static Annotation[] getAnnotations(Set<Field> fields, Set<Method> methods,
			Class<? extends Annotation> annotationClass) {
		return filterAnnotations(getAnnotations(fields, methods), annotationClass);
	}
}
