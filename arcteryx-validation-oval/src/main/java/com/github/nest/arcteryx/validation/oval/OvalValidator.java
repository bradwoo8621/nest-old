/**
 * 
 */
package com.github.nest.arcteryx.validation.oval;

import java.util.Collection;

import net.sf.oval.Validator;
import net.sf.oval.configuration.Configurer;

import com.github.nest.arcteryx.validation.oval.localization.message.OvalResourceBundleMessageResolver;

/**
 * Customized OVal validator which initialize the additional messages
 * 
 * @author brad.wu
 */
public class OvalValidator extends Validator {
	public OvalValidator() {
		super();
		initMessageBundleResolver();
	}

	public OvalValidator(Collection<Configurer> configurers) {
		super(configurers);
		initMessageBundleResolver();
	}

	public OvalValidator(Configurer... configurers) {
		super(configurers);
		initMessageBundleResolver();
	}

	/**
	 * add resource bundle
	 */
	protected void initMessageBundleResolver() {
		Validator.setMessageResolver(new OvalResourceBundleMessageResolver());
	}
}
