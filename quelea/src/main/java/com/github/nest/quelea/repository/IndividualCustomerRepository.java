/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.IndividualCustomer;

/**
 * individual customer repository
 * 
 * @author brad.wu
 */
public interface IndividualCustomerRepository extends CrudRepository<IndividualCustomer, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	IndividualCustomer findByRoleCode(String roleCode);
}
