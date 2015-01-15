/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import net.sf.oval.Check;
import net.sf.oval.constraint.AssertCheck;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.internal.constraints.BeanScript;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;

/**
 * script convertor
 * 
 * @author brad.wu
 */
public class BeanScriptConvertor extends AbstractOValBeanCheckConvertor<BeanScript> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<BeanScript> getSupportedConstraintType() {
		return BeanScript.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(BeanScript constraint) {
		AssertCheck check = new AssertCheck();
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
		check.setLang(lang);
		check.setExpr(expr);
		return check;
	}
}
