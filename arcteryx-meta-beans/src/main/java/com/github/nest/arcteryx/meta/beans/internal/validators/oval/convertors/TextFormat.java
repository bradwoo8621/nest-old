/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.ConstraintTarget;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.configuration.annotation.Constraint;
import net.sf.oval.configuration.annotation.Constraints;

/**
 * text format
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Constraint(checkWith = TextFormatCheck.class)
public @interface TextFormat {
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@Constraints
	public @interface List {
		/**
		 * The DateRange constraints.
		 */
		TextFormat[] value();

		/**
		 * Formula returning <code>true</code> if this constraint shall be
		 * evaluated and <code>false</code> if it shall be ignored for the
		 * current validation.
		 * <p>
		 * <b>Important:</b> The formula must be prefixed with the name of the
		 * scripting language that is used. E.g.
		 * <code>groovy:_this.amount > 10</code>
		 * <p>
		 * Available context variables are:<br>
		 * <b>_this</b> -&gt; the validated bean<br>
		 * <b>_value</b> -&gt; the value to validate (e.g. the field value,
		 * parameter value, method return value, or the validated bean for
		 * object level constraints)
		 */
		String when() default "";
	}

	/**
	 * <p>
	 * In case the constraint is declared for an array, collection or map this
	 * controls how the constraint is applied to it and it's child objects.
	 * 
	 * <p>
	 * <b>Default:</b> ConstraintTarget.VALUES
	 * 
	 * <p>
	 * <b>Note:</b> This setting is ignored for object types other than array,
	 * map and collection.
	 */
	ConstraintTarget[] appliesTo() default ConstraintTarget.VALUES;

	/**
	 * error code passed to the ConstraintViolation object
	 */
	String errorCode() default "com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.TextFormat";

	/**
	 * The format of the specified dates in a form understandable by the
	 * SimpleDateFormat class. Defaults to the default format style of the
	 * default locale.
	 */
	String format() default "";

	/**
	 * message to be used for the ContraintsViolatedException
	 * 
	 * @see ConstraintViolation
	 */
	String message() default "com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.TextFormat.violated";

	/**
	 * The associated constraint profiles.
	 */
	String[] profiles() default {};

	/**
	 * severity passed to the ConstraintViolation object
	 */
	int severity() default 0;

	/**
	 * An expression to specify where in the object graph relative from this
	 * object the expression should be applied.
	 * <p>
	 * Examples:
	 * <li>"owner" would apply this constraint to the current object's property
	 * <code>owner</code>
	 * <li>"owner.id" would apply this constraint to the current object's
	 * <code>owner</code>'s property <code>id</code>
	 * <li>"jxpath:owner/id" would use the JXPath implementation to traverse the
	 * object graph to locate the object where this constraint should be
	 * applied.
	 */
	String target() default "";
}
