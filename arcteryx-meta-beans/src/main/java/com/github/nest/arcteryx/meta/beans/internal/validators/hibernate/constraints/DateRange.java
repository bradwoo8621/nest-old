/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

/**
 * date range
 * 
 * @author brad.wu
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { DateRangeValidator.class })
public @interface DateRange {
	String message() default "{com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.DateRange.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link NotNull} annotations on the same element.
	 *
	 * @see javax.validation.constraints.NotNull
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		DateRange[] value();
	}

	/**
	 * The format of the specified dates in a form understandable by the
	 * SimpleDateFormat class. Defaults to the default format style of the
	 * default locale.
	 */
	String format() default "";

	/**
	 * The upper date compared against in the format specified with the
	 * dateFormat parameter. If not specified then no upper boundary check is
	 * performed.<br>
	 * Special values are:
	 * <ul>
	 * <li><code>now</code>
	 * <li><code>today</code>
	 * <li><code>yesterday</code>
	 * <li><code>tomorrow</code>
	 * </ul>
	 */
	String max() default "";

	/**
	 * exclude the upper date or not
	 * 
	 * @return
	 */
	boolean excludeMax() default false;

	/**
	 * The lower date compared against in the format specified with the
	 * dateFormat parameter. If not specified then no upper boundary check is
	 * performed.<br>
	 * Special values are:
	 * <ul>
	 * <li><code>now</code>
	 * <li><code>today</code>
	 * <li><code>yesterday</code>
	 * <li><code>tomorrow</code>
	 * </ul>
	 */
	String min() default "";

	/**
	 * exclude the lower date or not
	 * 
	 * @return
	 */
	boolean excludeMin() default false;

	/**
	 * Tolerance in milliseconds the validated value can be beyond the min/max
	 * limits. This is useful to compensate time differences in distributed
	 * environments where the clocks are not 100% in sync.
	 * 
	 * @return
	 */
	int tolerance() default 0;
}
