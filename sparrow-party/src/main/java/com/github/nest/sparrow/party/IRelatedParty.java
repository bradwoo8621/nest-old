/**
 * 
 */
package com.github.nest.sparrow.party;

/**
 * related party interface
 * 
 * @author brad.wu
 */
public interface IRelatedParty {
	/**
	 * get relationship
	 * 
	 * @return
	 */
	IRelationship getRelationship();

	/**
	 * get party
	 * 
	 * @return
	 */
	IParty getParty();
}
