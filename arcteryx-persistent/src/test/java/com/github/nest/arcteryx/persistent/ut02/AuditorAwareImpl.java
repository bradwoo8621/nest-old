/**
 * 
 */
package com.github.nest.arcteryx.persistent.ut02;

import org.springframework.data.domain.AuditorAware;

/**
 * @author brad.wu
 */
public class AuditorAwareImpl implements AuditorAware<Long> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public Long getCurrentAuditor() {
		return 1l;
	}
}
