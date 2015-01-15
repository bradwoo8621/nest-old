/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.localization.message;

import static net.sf.oval.Validator.getCollectionFactory;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import net.sf.oval.Validator;
import net.sf.oval.internal.Log;
import net.sf.oval.internal.util.Assert;
import net.sf.oval.localization.message.MessageResolver;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;

/**
 * for fix the resource bundle problem
 * 
 * @author brad.wu
 */
public class OvalResourceBundleMessageResolver implements MessageResolver {
	private static final Log LOG = Log.getLog(ResourceBundleMessageResolver.class);

	public static final ResourceBundleMessageResolver INSTANCE = new ResourceBundleMessageResolver();

	private final Map<ResourceBundle, List<String>> messageBundleKeys = getCollectionFactory().createMap(8);
	private final Map<Locale, List<ResourceBundle>> messageBundlesByLocale = getCollectionFactory().createMap(8);
	private Set<String> bundleLocations = new HashSet<String>();

	public OvalResourceBundleMessageResolver() {
		bundleLocations.add("net/sf/oval/Messages");
		bundleLocations.add("com/github/nest/arcteryx/validation/oval/Messages");
	}

	/**
	 * add message bundle
	 * 
	 * @param bundleLocation
	 */
	public void addMessageBundle(String bundleLocation) {
		Assert.argumentNotNull("bundleLocation", bundleLocation);
		if (!this.bundleLocations.contains(bundleLocation)) {
			synchronized (this.bundleLocations) {
				if (!this.bundleLocations.contains(bundleLocation)) {
					this.bundleLocations.add(bundleLocation);
				}
			}
		}
	}

	/**
	 * add message bundles
	 * 
	 * @param bundleLocation
	 */
	public void addMessageBundle(String... bundleLocations) {
		Assert.argumentNotNull("bundleLocations", bundleLocations);
		for (String bundleLocation : bundleLocations) {
			addMessageBundle(bundleLocation);
		}
	}

	/**
	 * add resource bundle
	 * 
	 * @param messageBundle
	 * @param locale
	 * @return
	 */
	protected boolean addMessageBundle(final ResourceBundle messageBundle, Locale locale) {
		// final List<ResourceBundle> messageBundles =
		// getMessageBundlesForLocale(locale);
		//
		// if (messageBundles.contains(messageBundle))
		// return false;
		//
		// messageBundles.add(0, messageBundle);
		//
		if (!messageBundleKeys.containsKey(messageBundle)) {
			final List<String> keys = getCollectionFactory().createList();
			for (final Enumeration<String> keysEnum = messageBundle.getKeys(); keysEnum.hasMoreElements();)
				keys.add(keysEnum.nextElement());
			messageBundleKeys.put(messageBundle, keys);
		}

		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.localization.message.MessageResolver#getMessage(java.lang.String)
	 */
	public String getMessage(final String key) {
		final Locale l = Validator.getLocaleProvider().getLocale();
		String msg = getMessage(key, l);
		if (msg == null && !l.equals((Locale.getDefault())))
			msg = getMessage(key, Locale.getDefault());
		return msg;
	}

	/**
	 * get message by given key and locale
	 * 
	 * @param key
	 * @param locale
	 * @return
	 */
	protected String getMessage(final String key, Locale locale) {
		final List<ResourceBundle> messageBundles = getMessageBundlesForLocale(locale);

		for (final ResourceBundle bundle : messageBundles) {
			final List<String> keys = messageBundleKeys.get(bundle);
			if (keys.contains(key))
				return bundle.getString(key);
		}
		return null;
	}

	/**
	 * get resource bundles by given locale. if no resource bundle was found,
	 * read resource bundles by given locations.
	 * 
	 * @param locale
	 * @return
	 */
	protected List<ResourceBundle> getMessageBundlesForLocale(Locale locale) {
		Assert.argumentNotNull("locale", locale);

		List<ResourceBundle> mbs = messageBundlesByLocale.get(locale);
		if (mbs == null) {
			synchronized (messageBundlesByLocale) {
				mbs = messageBundlesByLocale.get(locale);
				if (mbs == null) {
					mbs = new ArrayList<ResourceBundle>();

					// add the message bundle for the pre-built constraints
					for (String bundleLocation : this.bundleLocations) {
						try {
							ResourceBundle bundle = ResourceBundle.getBundle(bundleLocation, locale);
							addMessageBundle(bundle, locale);
							mbs.add(bundle);
						} catch (final MissingResourceException ex) {
							LOG.warn("No message bundle {3} for locale %s found.", ex, locale, bundleLocation);
						}
					}
					messageBundlesByLocale.put(locale, mbs);
				}
			}
		}
		return mbs;
	}
}
