/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import net.sf.oval.constraint.AssertURLCheck;
import net.sf.oval.constraint.AssertURLCheck.URIScheme;

/**
 * wrapper of permitted URI schemes of URL check
 * 
 * @author brad.wu
 */
public class URISchemesWrapper {
	private String scheme = null;

	/**
	 * add scheme
	 * 
	 * @param scheme
	 */
	public void set(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * set URI scheme array to URL check
	 * 
	 * @param check
	 */
	public void setToCheck(AssertURLCheck check) {
		if (this.scheme == null || this.scheme.trim().length() == 0) {
			return;
		}

		URIScheme[] schemes = check.getPermittedURISchemes();

		URIScheme[] array = new URIScheme[schemes == null ? 1 : (schemes.length + 1)];
		System.arraycopy(schemes, 0, array, 0, schemes.length);
		array[array.length - 1] = URIScheme.valueOf(this.scheme);
		check.setPermittedURISchemes(array);
	}
}
