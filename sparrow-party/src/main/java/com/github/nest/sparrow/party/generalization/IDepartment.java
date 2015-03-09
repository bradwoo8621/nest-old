/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IOrganization;

/**
 * department
 * 
 * @author brad.wu
 */
public interface IDepartment extends IOrganization {
	/**
	 * business supervisor department
	 * 
	 * @return
	 */
	IDepartment getBusinessSupervisor();
}
