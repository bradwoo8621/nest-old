/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.providers;

import net.sf.oval.Validator;
import net.sf.oval.localization.locale.LocaleProvider;
import net.sf.oval.localization.message.MessageResolver;
import net.sf.oval.localization.value.MessageValueFormatter;
import net.sf.oval.logging.LoggerFactory;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.IBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.AbstractOValBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.OValBeanValidator184;
import com.github.nest.arcteryx.validation.oval.localization.message.OvalResourceBundleMessageResolver;

/**
 * OVal bean validator provider
 * 
 * @author brad.wu
 */
public class OValBeanValidatorProvider implements IOperatorProvider {
	private Class<? extends AbstractOValBeanValidator> validatorClass = null;

	public OValBeanValidatorProvider() {
		OvalResourceBundleMessageResolver messageResolver = new OvalResourceBundleMessageResolver();
		messageResolver
				.addMessageBundle("/com/github/nest/arcteryx/meta/beans/internal/validators/oval/constraints/Messages");
		Validator.setMessageResolver(messageResolver);
	}

	/**
	 * @return the validatorClass
	 */
	public Class<? extends AbstractOValBeanValidator> getValidatorClass() {
		return this.validatorClass == null ? OValBeanValidator184.class : this.validatorClass;
	}

	/**
	 * @param validatorClass
	 *            the validatorClass to set
	 */
	public void setValidatorClass(Class<? extends AbstractOValBeanValidator> validatorClass) {
		this.validatorClass = validatorClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IBeanValidator createDefaultOperator(IResourceDescriptor descriptor) {
		IBeanValidator validator;
		try {
			validator = this.getValidatorClass().newInstance();
		} catch (InstantiationException e) {
			throw new ResourceException("Failed to create validator of [" + descriptor + "]");
		} catch (IllegalAccessException e) {
			throw new ResourceException("Failed to create validator of [" + descriptor + "]");
		}
		validator.setResourceDescriptor(descriptor);
		return validator;
	}

	/**
	 * set message resolver
	 * 
	 * @param resolver
	 */
	public void setMessageResolver(MessageResolver resolver) {
		Validator.setMessageResolver(resolver);
	}

	/**
	 * set message value formatter
	 * 
	 * @param formatter
	 */
	public void setMessageValueFormatter(MessageValueFormatter formatter) {
		Validator.setMessageValueFormatter(formatter);
	}

	/**
	 * set logger factory
	 * 
	 * @param loggerFactory
	 */
	public void setLoggerFactory(LoggerFactory loggerFactory) {
		Validator.setLoggerFactory(loggerFactory);
	}

	/**
	 * set locale provider
	 * 
	 * @param localeProvider
	 */
	public void setLocaleProvider(LocaleProvider localeProvider) {
		Validator.setLocaleProvider(localeProvider);
	}
}
