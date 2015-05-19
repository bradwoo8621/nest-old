/**
 * 
 */
package com.github.nest.arcteryx.persistent.ut02;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.github.nest.arteryx.persistent.AbstractAuditable;

/**
 * @author brad.wu
 *
 */
@Entity(name = "UT")
@Table(name = "T_UT_TABLE")
@SequenceGenerator(name = "S_UT_TABLE", sequenceName = "S_UT_TABLE")
public class UtTable extends AbstractAuditable {
	private static final long serialVersionUID = -7978329107863747323L;

	@Id
	@Column(name = "UT_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_UT_TABLE")
	private Long utId = null;

	@Column(name = "UT_NAME")
	private String name = null;

	/**
	 * @return the utId
	 */
	public Long getUtId() {
		return utId;
	}

	/**
	 * @param utId
	 *            the utId to set
	 */
	public void setUtId(Long utId) {
		this.utId = utId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
