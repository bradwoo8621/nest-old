/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.oval.Check;
import net.sf.oval.ConstraintSet;
import net.sf.oval.ConstraintTarget;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.collection.CollectionFactory;
import net.sf.oval.collection.CollectionFactoryJDKImpl;
import net.sf.oval.collection.CollectionFactoryJavalutionImpl;
import net.sf.oval.collection.CollectionFactoryTroveImpl;
import net.sf.oval.configuration.Configurer;
import net.sf.oval.constraint.AssertConstraintSet;
import net.sf.oval.constraint.AssertConstraintSetCheck;
import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.AssertFieldConstraintsCheck;
import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.AssertValidCheck;
import net.sf.oval.context.ConstructorParameterContext;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.MethodParameterContext;
import net.sf.oval.context.MethodReturnValueContext;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.FieldNotFoundException;
import net.sf.oval.exception.OValException;
import net.sf.oval.exception.UndefinedConstraintSetException;
import net.sf.oval.exception.ValidationFailedException;
import net.sf.oval.internal.ClassChecks;
import net.sf.oval.internal.ContextCache;
import net.sf.oval.internal.Log;
import net.sf.oval.internal.util.ArrayUtils;
import net.sf.oval.internal.util.Assert;
import net.sf.oval.internal.util.ReflectionUtils;
import net.sf.oval.ogn.ObjectGraphNavigationResult;

/**
 * OVal validator.<br>
 * Changes:<br>
 * <ul>
 * <li>
 * add path of value to be validated into violation. for collection and array,
 * use index (note the index of set or bag is get from iterator). for map, use
 * key of element.</li>
 * <li>remove the super class validation.</li>
 * </ul>
 * <strong>Code is copy from {@linkplain Validator} 1.84. if use OVal 1.8.4+,
 * please verify it is still worked or not.
 * 
 * @author brad.wu
 */
public class OValValidator184 extends Validator {
	private static final Log LOG = Log.getLog(OValValidator184.class);
	private static CollectionFactory collectionFactory = _createDefaultCollectionFactory();

	public OValValidator184(Configurer... configurers) {
		super(configurers);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.Validator#checkConstraint(java.util.List,
	 *      net.sf.oval.Check, java.lang.Object, java.lang.Object,
	 *      net.sf.oval.context.OValContext, java.lang.String[], boolean)
	 */
	@Override
	protected void checkConstraint(List<ConstraintViolation> violations, Check check, Object validatedObject,
			Object valueToValidate, OValContext context, String[] profiles, boolean isContainerValue)
			throws OValException {
		this.checkConstraint(violations, check, validatedObject, valueToValidate, context, profiles, isContainerValue,
				null);
	}

	/**
	 * 
	 * @param violations
	 * @param check
	 * @param validatedObject
	 * @param valueToValidate
	 * @param context
	 * @param profiles
	 * @param isContainerValue
	 * @param index
	 */
	protected void checkConstraint(List<ConstraintViolation> violations, Check check, Object validatedObject,
			Object valueToValidate, OValContext context, String[] profiles, boolean isContainerValue,
			IContainerIndex index) {
		if (!isAnyProfileEnabled(check.getProfiles(), profiles))
			return;

		if (!check.isActive(validatedObject, valueToValidate, this))
			return;

		final ConstraintTarget[] targets = check.getAppliesTo();

		// only process the target expression if we are not already on a value
		// inside the container object (collection, array, map)
		if (!isContainerValue) {
			String target = check.getTarget();
			if (target != null) {
				target = target.trim();
				if (target.length() > 0) {
					if (valueToValidate == null)
						return;
					final String[] chunks = target.split(":", 2);
					final String ognId, path;
					if (chunks.length == 1) {
						ognId = "";
						path = chunks[0];
					} else {
						ognId = chunks[0];
						path = chunks[1];
					}
					final ObjectGraphNavigationResult result = ognRegistry.getObjectGraphNavigator(ognId) //
							.navigateTo(valueToValidate, path);
					if (result == null)
						return;
					validatedObject = result.targetParent;
					valueToValidate = result.target;
					if (result.targetAccessor instanceof Field)
						context = ContextCache.getFieldContext((Field) result.targetAccessor);
					else
						context = ContextCache.getMethodReturnValueContext((Method) result.targetAccessor);
				}
			}
		}

		final Class<?> compileTimeType = context.getCompileTimeType();

		final boolean isCollection = valueToValidate != null ? //
		valueToValidate instanceof Collection<?>
				: //
				Collection.class.isAssignableFrom(compileTimeType);
		final boolean isMap = !isCollection && //
				(valueToValidate != null ? //
				valueToValidate instanceof Map<?, ?>
						: //
						Map.class.isAssignableFrom(compileTimeType));
		final boolean isArray = !isCollection && !isMap && //
				(valueToValidate != null ? //
				valueToValidate.getClass().isArray()
						: //
						compileTimeType.isArray());
		final boolean isContainer = isCollection || isMap || isArray;

		if (isContainer && valueToValidate != null) {
			if (isCollection) {
				if (ArrayUtils.containsSame(targets, ConstraintTarget.VALUES)) {
					int itemIndex = 0;
					for (final Object item : (Collection<?>) valueToValidate) {
						IContainerIndex containerIndex = new IContainerIndex.CollectionIndex(itemIndex++);
						checkConstraint(violations, check, validatedObject, item, context, profiles, true,
								containerIndex);
					}
				}
			} else if (isMap) {
				if (ArrayUtils.containsSame(targets, ConstraintTarget.KEYS)) {
					for (final Object item : ((Map<?, ?>) valueToValidate).keySet()) {
						IContainerIndex containerIndex = new IContainerIndex.MapKeyIndex(item);
						checkConstraint(violations, check, validatedObject, item, context, profiles, true,
								containerIndex);
					}
				}
				if (ArrayUtils.containsSame(targets, ConstraintTarget.VALUES)) {
					for (final Map.Entry<?, ?> entry : ((Map<?, ?>) valueToValidate).entrySet()) {
						IContainerIndex containerIndex = new IContainerIndex.MapValueIndex(entry.getKey());
						checkConstraint(violations, check, validatedObject, entry.getValue(), context, profiles, true,
								containerIndex);
					}
					for (final Object item : ((Map<?, ?>) valueToValidate).values()) {
						checkConstraint(violations, check, validatedObject, item, context, profiles, true);
					}
				}
			} else if (ArrayUtils.containsSame(targets, ConstraintTarget.VALUES)) {
				int itemIndex = 0;
				for (final Object item : ArrayUtils.asList(valueToValidate)) {
					IContainerIndex containerIndex = new IContainerIndex.CollectionIndex(itemIndex++);
					checkConstraint(violations, check, validatedObject, item, context, profiles, true, containerIndex);
				}
			}
		}

		if (isContainerValue || !isContainer || isContainer
				&& ArrayUtils.containsSame(targets, ConstraintTarget.CONTAINER))
			internalCheckConstraint(violations, check, validatedObject, valueToValidate, context, profiles, index);
	}

	/**
	 * internal check constraints
	 * 
	 * @param violations
	 * @param check
	 * @param validatedObject
	 * @param valueToValidate
	 * @param context
	 * @param profiles
	 * @param index
	 */
	protected void internalCheckConstraint(final List<ConstraintViolation> violations, final Check check,
			final Object validatedObject, final Object valueToValidate, final OValContext context,
			final String[] profiles, IContainerIndex index) {
		/*
		 * special handling of the AssertValid constraint
		 */
		if (check instanceof AssertValidCheck) {
			checkConstraintAssertValid(violations, (AssertValidCheck) check, validatedObject, valueToValidate, context,
					profiles, index);
			return;
		}

		/*
		 * special handling of the FieldConstraints constraint
		 */
		if (check instanceof AssertConstraintSetCheck) {
			checkConstraintAssertConstraintSet(violations, (AssertConstraintSetCheck) check, validatedObject,
					valueToValidate, context, profiles, index);
			return;
		}

		/*
		 * special handling of the FieldConstraints constraint
		 */
		if (check instanceof AssertFieldConstraintsCheck) {
			checkConstraintAssertFieldConstraints(violations, (AssertFieldConstraintsCheck) check, validatedObject,
					valueToValidate, context, profiles, index);
			return;
		}

		/*
		 * standard constraints handling
		 */
		if (!check.isSatisfied(validatedObject, valueToValidate, context, this)) {
			final String errorMessage = renderMessage(context, valueToValidate, check.getMessage(),
					check.getMessageVariables());
			violations.add(new OValConstraintViolation(check, errorMessage, validatedObject, valueToValidate, context,
					index));
		}
	}

	/**
	 * check {@linkplain AssertValid} constraint
	 * 
	 * @param violations
	 * @param check
	 * @param validatedObject
	 * @param valueToValidate
	 * @param context
	 * @param profiles
	 * @param index
	 * @throws OValException
	 */
	protected void checkConstraintAssertValid(final List<ConstraintViolation> violations, final AssertValidCheck check,
			final Object validatedObject, final Object valueToValidate, final OValContext context,
			final String[] profiles, IContainerIndex index) throws OValException {
		if (valueToValidate == null)
			return;

		// ignore circular dependencies
		if (isCurrentlyValidated(valueToValidate))
			return;

		final List<ConstraintViolation> additionalViolations = collectionFactory.createList();
		validateInvariants(valueToValidate, additionalViolations, profiles);

		if (additionalViolations.size() != 0) {
			final String errorMessage = renderMessage(context, valueToValidate, check.getMessage(),
					check.getMessageVariables());

			List<OValConstraintViolation> subViolations = collectionFactory.createList();
			for (ConstraintViolation violation : additionalViolations) {
				subViolations.add((OValConstraintViolation) violation);
			}
			violations.add(new OValConstraintViolation(check, errorMessage, validatedObject, valueToValidate, context,
					index, subViolations));
		}
	}

	/**
	 * check {@linkplain AssertConstraintSet} constraint
	 * 
	 * @param violations
	 * @param check
	 * @param validatedObject
	 * @param valueToValidate
	 * @param context
	 * @param profiles
	 * @param index
	 * @throws OValException
	 */
	protected void checkConstraintAssertConstraintSet(final List<ConstraintViolation> violations,
			final AssertConstraintSetCheck check, final Object validatedObject, final Object valueToValidate,
			final OValContext context, final String[] profiles, IContainerIndex index) throws OValException {
		final ConstraintSet cs = getConstraintSet(check.getId());

		if (cs == null)
			throw new UndefinedConstraintSetException(check.getId());

		final Collection<Check> referencedChecks = cs.getChecks();

		if (referencedChecks != null && referencedChecks.size() > 0)
			for (final Check referencedCheck : referencedChecks)
				checkConstraint(violations, referencedCheck, validatedObject, valueToValidate, context, profiles,
						false, index);
	}

	/**
	 * check {@linkplain AssertFieldConstraints} constraint
	 * 
	 * @param violations
	 * @param check
	 * @param validatedObject
	 * @param valueToValidate
	 * @param context
	 * @param profiles
	 * @param index
	 * @throws OValException
	 */
	protected void checkConstraintAssertFieldConstraints(final List<ConstraintViolation> violations,
			final AssertFieldConstraintsCheck check, final Object validatedObject, final Object valueToValidate,
			final OValContext context, final String[] profiles, IContainerIndex index) throws OValException {
		final Class<?> targetClass;

		/*
		 * set the targetClass based on the validation context
		 */
		if (check.getDeclaringClass() != null && check.getDeclaringClass() != Void.class)
			targetClass = check.getDeclaringClass();
		else if (context instanceof ConstructorParameterContext)
			// the class declaring the field must either be the class declaring
			// the constructor or one of its super
			// classes
			targetClass = ((ConstructorParameterContext) context).getConstructor().getDeclaringClass();
		else if (context instanceof MethodParameterContext)
			// the class declaring the field must either be the class declaring
			// the method or one of its super classes
			targetClass = ((MethodParameterContext) context).getMethod().getDeclaringClass();
		else if (context instanceof MethodReturnValueContext)
			// the class declaring the field must either be the class declaring
			// the getter or one of its super classes
			targetClass = ((MethodReturnValueContext) context).getMethod().getDeclaringClass();
		else
			// the lowest class that is expected to declare the field (or one of
			// its super classes)
			targetClass = validatedObject.getClass();

		// the name of the field whose constraints shall be used
		String fieldName = check.getFieldName();

		/*
		 * calculate the field name based on the validation context if the
		 * 
		 * @AssertFieldConstraints constraint didn't specify the field name
		 */
		if (fieldName == null || fieldName.length() == 0)
			if (context instanceof ConstructorParameterContext)
				fieldName = ((ConstructorParameterContext) context).getParameterName();
			else if (context instanceof MethodParameterContext)
				fieldName = ((MethodParameterContext) context).getParameterName();
			else if (context instanceof MethodReturnValueContext)
				fieldName = ReflectionUtils.guessFieldName(((MethodReturnValueContext) context).getMethod());

		/*
		 * find the field based on fieldName and targetClass
		 */
		final Field field = ReflectionUtils.getFieldRecursive(targetClass, fieldName);

		if (field == null)
			throw new FieldNotFoundException("Field <" + fieldName + "> not found in class <" + targetClass
					+ "> or its super classes.");

		final ClassChecks cc = getClassChecks(field.getDeclaringClass());
		final Collection<Check> referencedChecks = cc.checksForFields.get(field);
		if (referencedChecks != null && referencedChecks.size() > 0)
			for (final Check referencedCheck : referencedChecks)
				checkConstraint(violations, referencedCheck, validatedObject, valueToValidate, context, profiles,
						false, index);
	}

	/**
	 * copy from super class
	 * 
	 * @return
	 */
	protected static CollectionFactory _createDefaultCollectionFactory() {
		// if Javolution collection classes are found use them by default
		if (ReflectionUtils.isClassPresent("javolution.util.FastMap")
				&& ReflectionUtils.isClassPresent("javolution.util.FastSet")
				&& ReflectionUtils.isClassPresent("javolution.util.FastTable")) {
			LOG.info("javolution.util collection classes are available.");

			return new CollectionFactoryJavalutionImpl();
		}
		// else if Trove collection classes are found use them by default
		else if (ReflectionUtils.isClassPresent("gnu.trove.THashMap")
				&& ReflectionUtils.isClassPresent("gnu.trove.THashSet")) {
			LOG.info("gnu.trove collection classes are available.");

			return new CollectionFactoryTroveImpl();
		}
		// else use JDK collection classes by default
		else
			return new CollectionFactoryJDKImpl();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.Validator#validateInvariants(java.lang.Object,
	 *      java.util.List, java.lang.String[])
	 */
	@Override
	protected void validateInvariants(Object validatedObject, List<ConstraintViolation> violations, String[] profiles)
			throws IllegalArgumentException, ValidationFailedException {
		Assert.argumentNotNull("validatedObject", validatedObject);

		if (validatedObject instanceof Class<?>) {
			super.validateInvariants(validatedObject, violations, profiles);
		} else {
			currentlyValidatedObjects.get().getLast().add(validatedObject);
			_validateObjectInvariants(validatedObject, validatedObject.getClass(), violations, profiles);
		}
	}

	/**
	 * validate object invariants
	 * 
	 * @param validatedObject
	 * @param clazz
	 * @param violations
	 * @param profiles
	 * @throws ValidationFailedException
	 */
	protected void _validateObjectInvariants(final Object validatedObject, final Class<?> clazz,
			final List<ConstraintViolation> violations, final String[] profiles) throws ValidationFailedException {
		assert validatedObject != null;
		assert clazz != null;
		assert violations != null;

		// abort if the root class has been reached
		if (clazz == Object.class)
			return;

		try {
			final ClassChecks cc = getClassChecks(clazz);

			// validate field constraints
			for (final Field field : cc.constrainedFields) {
				final Collection<Check> checks = cc.checksForFields.get(field);

				if (checks != null && checks.size() > 0) {
					final FieldContext ctx = ContextCache.getFieldContext(field);
					final Object valueToValidate = resolveValue(ctx, validatedObject);

					for (final Check check : checks)
						checkConstraint(violations, check, validatedObject, valueToValidate, ctx, profiles, false);
				}
			}

			// validate constraints on getter methods
			for (final Method getter : cc.constrainedMethods) {
				final Collection<Check> checks = cc.checksForMethodReturnValues.get(getter);

				if (checks != null && checks.size() > 0) {
					final MethodReturnValueContext ctx = ContextCache.getMethodReturnValueContext(getter);
					final Object valueToValidate = resolveValue(ctx, validatedObject);

					for (final Check check : checks)
						checkConstraint(violations, check, validatedObject, valueToValidate, ctx, profiles, false);
				}
			}

			// validate object constraints
			if (cc.checksForObject.size() > 0) {
				final OValContext ctx = ContextCache.getClassContext(clazz);
				for (final Check check : cc.checksForObject)
					checkConstraint(violations, check, validatedObject, validatedObject, ctx, profiles, false);
			}

			// if the super class is annotated to be validatable also validate
			// it against the object
			// remove the recursive validation
			// _validateObjectInvariants(validatedObject, clazz.getSuperclass(),
			// violations, profiles);
		} catch (final OValException ex) {
			throw new ValidationFailedException("Object validation failed. Class: " + clazz + " Validated object: "
					+ validatedObject, ex);
		}
	}
}
