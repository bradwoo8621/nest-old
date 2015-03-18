/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IIndividual;

/**
 * employee
 * 
 * @author brad.wu
 */
public interface IEmployee extends IIndividual {
	/**
	 * get department
	 * 
	 * @return
	 */
	IDepartment getDepartment();

	/**
	 * get branch
	 * 
	 * @return
	 */
	IBranch getBranch();
}
