/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.sf.oval.Check;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.ClassContext;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.MethodReturnValueContext;
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraintViolation#getRelativePath()
	 */
	@Override
	public String getRelativePath() {
		OValContext context = this.getCheckDeclaringContext();
		// name
		String name = convertContextToName(context);
		// index
		String indexKey = null;
		if (getIndex() != null) {
			indexKey = getIndex().getIndexKey();
		}
		// target
		String target = null;
		if (StringUtils.isNotEmpty(this.getTarget())) {
			target = this.getTarget();
			int pos = target.indexOf(':');
			if (pos != -1) {
				// jxpath target
				target = target.substring(pos + 1).replace('/', '.');
			}
		}

		StringBuilder sb = new StringBuilder(name);
		if (indexKey != null) {
			sb.append('[').append(indexKey).append(']');
		}
		if (target != null) {
			sb.append('.').append(target);
		}
		return sb.toString();
	}

	/**
	 * convert context to name
	 * 
	 * @param context
	 * @return
	 */
	protected String convertContextToName(OValContext context) {
		String name;
		if (context instanceof MethodReturnValueContext) {
			Method method = ((MethodReturnValueContext) context).getMethod();
			name = method.getName();
			if (name.startsWith("get")) {
				name = name.substring(3);
			} else if (name.startsWith("is")) {
				name = name.substring(2);
			}
			if (name.length() == 1) {
				name = name.toLowerCase();
			} else {
				name = name.substring(0, 1).toLowerCase() + name.substring(1);
			}
		} else if (context instanceof FieldContext) {
			name = ((FieldContext) context).getField().getName();
		} else if (context instanceof ClassContext) {
			name = "self";
		} else {
			name = context.toString();
		}
		return name;
	}
}
