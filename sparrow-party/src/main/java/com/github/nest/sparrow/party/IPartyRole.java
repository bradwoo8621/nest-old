/**
 * 
 */
package com.github.nest.sparrow.party;

import com.github.nest.sparrow.enums.ICodedEnumItem;

/**
 * party role interface
 * 
 * @author brad.wu
 */
public interface IPartyRole extends ICodedEnumItem {
	/**
	 * get code of party role. code must be unique key of whole system.
	 * 
	 * @return
	 */
	String getCode();
}
