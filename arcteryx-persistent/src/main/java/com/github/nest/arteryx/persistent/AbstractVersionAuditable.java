/**
 * 
 */
package com.github.nest.arteryx.persistent;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * versionable and auditable
 * 
 * @author brad.wu
 */
@MappedSuperclass
public abstract class AbstractVersionAuditable extends AbstractAuditable implements IVersionable {
	private static final long serialVersionUID = -6977977174556551589L;

	@Version
	@Column(name = "OPT_LOCK")
	private Long versionNumber = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IVersionable#getVersionNumber()
	 */
	@Override
	public Long getVersionNumber() {
		return this.versionNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IVersionable#setVersionNumber(java.lang.Long)
	 */
	@Override
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
}
