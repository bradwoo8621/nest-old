/**
 * 
 */
package com.github.nest.quelea.internal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.nest.arteryx.persistent.AbstractVersionAuditable;
import com.github.nest.quelea.internal.support.IPartyNameStrategyFactory;
import com.github.nest.quelea.model.IAccount;
import com.github.nest.quelea.model.IAddress;
import com.github.nest.quelea.model.IParty;
import com.github.nest.quelea.model.IRelation;

/**
 * party
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_PARTY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ID_NUMBER")
public abstract class Party extends AbstractVersionAuditable implements IParty {
	private static final long serialVersionUID = 6672583372258942280L;

	@Autowired
	@Qualifier("partyNameStrategyFactory")
	private IPartyNameStrategyFactory partyNameStrategyFactory;

	@Id
	@Column(name = "PARTY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARTY")
	private Long partyId = null;

	@Column(name = "PARTY_TYPE_CODE")
	private String partyTypeCode = null;

	@Column(name = "ID_NUMBER")
	private String idNumber = null;

	@OneToMany
	private List<IAccount> accounts = null;

	@OneToMany
	private List<IAddress> addresses = null;

	@OneToMany
	private List<IRelation> relations = null;

	@Column(name = "IS_ENABLED")
	private Boolean enabled = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#getPartyId()
	 */
	@Override
	public Long getPartyId() {
		return this.partyId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setPartyId(java.lang.Long)
	 */
	@Override
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#getPartyTypeCode()
	 */
	@Override
	public String getPartyTypeCode() {
		return this.partyTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setPartyTypeCode(java.lang.String)
	 */
	@Override
	public void setPartyTypeCode(String partyTypeCode) {
		this.partyTypeCode = partyTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#getIdNumber()
	 */
	@Override
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setIdNumber(java.lang.String)
	 */
	@Override
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#getAddresses()
	 */
	@Override
	public List<IAddress> getAddresses() {
		return this.addresses;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setAddresses(java.util.List)
	 */
	@Override
	public void setAddresses(List<IAddress> addresses) {
		this.addresses = addresses;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#getAccounts()
	 */
	@Override
	public List<IAccount> getAccounts() {
		return this.accounts;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setAccounts(java.util.List)
	 */
	@Override
	public void setAccounts(List<IAccount> accounts) {
		this.accounts = accounts;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#getRelations()
	 */
	@Override
	public List<IRelation> getRelations() {
		return this.relations;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setRelations(java.util.List)
	 */
	@Override
	public void setRelations(List<IRelation> relations) {
		this.relations = relations;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#isEnabled()
	 */
	@Override
	public Boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IParty#setEnabled(java.lang.Boolean)
	 */
	@Override
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * get party name by {@linkplain IPartyNameStrategyFactory}
	 * 
	 * @see com.github.nest.quelea.model.IParty#getPartyName()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Column(name = "PARTY_NAME")
	public String getPartyName() {
		return this.getPartyNameStrategyFactory().getPartyNameStrategy(this).getPartyName(this);
	}

	/**
	 * do nothing
	 * 
	 * @see com.github.nest.quelea.model.IParty#setPartyName(java.lang.String)
	 */
	@Override
	public void setPartyName(String partyName) {
	}

	/**
	 * @return the partyNameStrategyFactory
	 */
	protected IPartyNameStrategyFactory getPartyNameStrategyFactory() {
		return partyNameStrategyFactory;
	}
}
