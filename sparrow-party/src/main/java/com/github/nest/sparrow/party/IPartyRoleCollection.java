/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Iterator;

/**
 * party role collection.
 * 
 * @author brad.wu
 */
public interface IPartyRoleCollection {
	// party can be defined as individual and organization
	String INDIVIDUAL = "IND";
	String ORGANIZATION = "ORG";

	// party can be defined as more categories
	String CUSTOMER = "CST";
	String EMPLOYEE = "EMP";
	

	/**
	 * get role by given party role code
	 * 
	 * @param partyRoleCode
	 * @return
	 */
	IPartyRole get(String partyRoleCode);

	/**
	 * iterate the party roles
	 * 
	 * @return
	 */
	Iterator<IPartyRole> iterator();

	/**
	 * check the given party role code is existed in collection or not
	 * 
	 * @param partyRoleCode
	 * @return
	 */
	boolean isLegal(String partyRoleCode);

	/**
	 * convert all party roles to array
	 * 
	 * @return
	 */
	IPartyRole[] toArray();
}
