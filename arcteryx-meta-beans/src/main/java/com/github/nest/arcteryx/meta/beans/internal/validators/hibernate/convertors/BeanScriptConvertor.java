/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.defs.ScriptAssertDef;
import org.hibernate.validator.constraints.ScriptAssert;

import com.github.nest.arcteryx.meta.beans.constraints.BeanScriptConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateErrorCodeRegistry;

/**
 * bean script convertor
 * 
 * @author brad.wu
 */
public class BeanScriptConvertor extends AbstractHibernateConstraintConvertor<BeanScriptConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#registerErrorCode()
	 */
	@Override
	protected void registerErrorCode() {
		HibernateErrorCodeRegistry.registerErrorCode(ScriptAssert.class, BeanScriptConstraint.class.getSimpleName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AbstractHibernateConstraintConvertor#createConstraintDef(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ConstraintDef createConstraintDef(BeanScriptConstraint constraint) {
		String script = constraint.getScript();
		if (StringUtils.isBlank(script)) {
			throw new BeanValidationException("Script cannot be empty string.");
		}

		String lang = null;
		String expr = null;
		int pos = script.indexOf(':');
		if (pos == -1) {
			// default language is groovy
			lang = "groovy";
			expr = script;
		} else {
			lang = script.substring(0, pos);
			expr = script.substring(pos + 1);
		}
		return new ScriptAssertDef().lang(lang).script(expr);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<BeanScriptConstraint> getSupportedConstraintType() {
		return BeanScriptConstraint.class;
	}
}
