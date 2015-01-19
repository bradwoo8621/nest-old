/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints;

import static net.sf.oval.Validator.getCollectionFactory;

import java.io.Serializable;
import java.util.Map;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints.TheNumber;

/**
 * number check
 * 
 * @author brad.wu
 */
public class TheNumberCheck extends AbstractAnnotationCheck<TheNumber> {
	private static final long serialVersionUID = -8382185790571457677L;

	private double min = Double.NEGATIVE_INFINITY;
	private boolean excludeMin = false;
	private double max = Double.POSITIVE_INFINITY;
	private boolean excludeMax = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.Check#isSatisfied(java.lang.Object, java.lang.Object,
	 *      net.sf.oval.context.OValContext, net.sf.oval.Validator)
	 */
	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator)
			throws OValException {
		if (valueToValidate == null)
			return true;

		double value = 0;
		if (valueToValidate instanceof java.lang.Number) {
			value = ((java.lang.Number) valueToValidate).doubleValue();
		} else {
			try {
				value = Double.parseDouble(valueToValidate.toString());
			} catch (NumberFormatException e) {
				return false;
			}
		}
		// validate
		boolean pass = true;
		if (this.getMin() != Double.NEGATIVE_INFINITY) {
			if (this.isExcludeMin()) {
				pass = value > this.getMin();
			} else {
				pass = value >= this.getMin();
			}
		}
		if (pass) {
			if (this.getMax() != Double.POSITIVE_INFINITY) {
				if (this.isExcludeMax()) {
					pass = value < this.getMax();
				} else {
					pass = value <= this.getMax();
				}
			}
		}
		return pass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.configuration.annotation.AbstractAnnotationCheck#configure(java.lang.annotation.Annotation)
	 */
	@Override
	public void configure(TheNumber constraintAnnotation) {
		super.configure(constraintAnnotation);
		this.setMin(constraintAnnotation.min());
		this.setMax(constraintAnnotation.max());
		this.setExcludeMin(constraintAnnotation.excludeMin());
		this.setExcludeMax(constraintAnnotation.excludeMax());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.AbstractCheck#createMessageVariables()
	 */
	@Override
	protected Map<String, ? extends Serializable> createMessageVariables() {
		final Map<String, String> messageVariables = getCollectionFactory().createMap(4);
		messageVariables.put("min", String.valueOf(this.getMin()));
		messageVariables.put("excludeMin", String.valueOf(this.isExcludeMin()));
		messageVariables.put("max", String.valueOf(this.getMax()));
		messageVariables.put("excludeMax", String.valueOf(this.isExcludeMax()));
		return messageVariables;
	}

	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(double min) {
		this.min = min;
		requireMessageVariablesRecreation();
	}

	/**
	 * @return the excludeMin
	 */
	public boolean isExcludeMin() {
		return excludeMin;
	}

	/**
	 * @param excludeMin
	 *            the excludeMin to set
	 */
	public void setExcludeMin(boolean excludeMin) {
		this.excludeMin = excludeMin;
		requireMessageVariablesRecreation();
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(double max) {
		this.max = max;
		requireMessageVariablesRecreation();
	}

	/**
	 * @return the excludeMax
	 */
	public boolean isExcludeMax() {
		return excludeMax;
	}

	/**
	 * @param excludeMax
	 *            the excludeMax to set
	 */
	public void setExcludeMax(boolean excludeMax) {
		this.excludeMax = excludeMax;
		requireMessageVariablesRecreation();
	}
}
