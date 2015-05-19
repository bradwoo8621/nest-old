/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;

import com.github.nest.quelea.codes.IRelationType;

/**
 * relationship between parties
 * 
 * @author brad.wu
 */
public interface IRelation extends Serializable {
	/**
	 * get relation id
	 * 
	 * @return
	 */
	Long getRelationId();

	/**
	 * set relation id
	 * 
	 * @param relationId
	 */
	void setRelationId(Long relationId);

	/**
	 * get relation type code
	 * 
	 * @return
	 * @see IRelationType
	 */
	String getRelationTypeCode();

	/**
	 * set relation type code
	 * 
	 * @param relationTypeCode
	 * @see IRelationType
	 */
	void setRelationTypeCode(String relationTypeCode);

	/**
	 * get party id
	 * 
	 * @return
	 */
	Long getPartyId();

	/**
	 * set party id
	 * 
	 * @param partyId
	 */
	void setPartyId(Long partyId);
}
