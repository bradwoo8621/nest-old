/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.util.LinkedList;
import java.util.List;

import net.sf.oval.Check;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.OValContext;

import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.IConstraintViolation;

/**
 * Oval constraint violation. <br>
 * TODO How to build message and hierarchy(target), i18n?
 * 
 * @author brad.wu
 */
public class OValConstraintViolation extends ConstraintViolation implements IConstraintViolation {
	private static final long serialVersionUID = 6836213426591882028L;
	private IContainerIndex index = null;
	private String target = null;
	private String when = null;

	public OValConstraintViolation(Check check, String message, Object validatedObject, Object invalidValue,
			OValContext context, IContainerIndex index, OValConstraintViolation... causes) {
		super(check, message, validatedObject, invalidValue, context, causes);
		this.index = index;
		this.target = check.getTarget();
		this.when = check.getWhen();
	}

	public OValConstraintViolation(Check check, String message, Object validatedObject, Object invalidValue,
			OValContext context, IContainerIndex index, List<OValConstraintViolation> causes) {
		this(check, message, validatedObject, invalidValue, context, index, causes
				.toArray(new OValConstraintViolation[causes.size()]));
	}

	public OValConstraintViolation(Check check, String message, Object validatedObject, Object invalidValue,
			OValContext context, IContainerIndex index) {
		super(check, message, validatedObject, invalidValue, context);
		this.index = index;
		this.target = check.getTarget();
		this.when = check.getWhen();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getValueToValidate()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValueToValidate() {
		return (T) this.getInvalidValue();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getBeanToValidate()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBeanToValidate() {
		return (T) this.getValidatedObject();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getConstraintSeverity()
	 */
	@Override
	public ConstraintSeverity getConstraintSeverity() {
		return ConstraintSeverity.convert(this.getSeverity());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getConstraintCauses()
	 */
	@Override
	public List<IConstraintViolation> getConstraintCauses() {
		ConstraintViolation[] originalViolations = this.getCauses();
		List<IConstraintViolation> violations = new LinkedList<IConstraintViolation>();
		if (originalViolations != null) {
			for (ConstraintViolation violation : originalViolations) {
				violations.add((IConstraintViolation) violation);
			}
		}
		return violations;
	}

	/**
	 * @return the index
	 */
	public IContainerIndex getIndex() {
		return index;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @return the when
	 */
	public String getWhen() {
		return when;
	}
}
