/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.github.nest.quelea.codes.PartyRelationType;

/**
 * relationship between parties
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_PARTY_RELATION")
@SequenceGenerator(name = "S_PARTY_RELATION", sequenceName = "S_PARTY_RELATION")
public class Relation implements Serializable {
	private static final long serialVersionUID = 4996124121636963384L;

	@Id
	@Column(name = "RELATION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARTY_RELATION")
	private Long relationId = null;

	@Column(name = "RELATION_TYPE_CODE")
	private String relationTypeCode = null;

	@Column(name = "ANOTHER_PARTY_ID")
	private Long anotherPartyId = null;

	@ManyToOne
	@JoinColumn(name = "PARTY_ID", nullable = false)
	private Party party = null;

	/**
	 * @return the relationId
	 */
	public Long getRelationId() {
		return relationId;
	}

	/**
	 * @param relationId
	 *            the relationId to set
	 */
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	/**
	 * @return the relationTypeCode
	 * @see PartyRelationType
	 */
	public String getRelationTypeCode() {
		return relationTypeCode;
	}

	/**
	 * @param relationTypeCode
	 *            the relationTypeCode to set
	 * @see PartyRelationType
	 */
	public void setRelationTypeCode(String relationTypeCode) {
		this.relationTypeCode = relationTypeCode;
	}

	/**
	 * @return the anotherPartyId
	 */
	public Long getAnotherPartyId() {
		return anotherPartyId;
	}

	/**
	 * @param anotherPartyId
	 *            the anotherPartyId to set
	 */
	public void setAnotherPartyId(Long anotherPartyId) {
		this.anotherPartyId = anotherPartyId;
	}

	/**
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * @param party the party to set
	 */
	public void setParty(Party party) {
		this.party = party;
	}
}
