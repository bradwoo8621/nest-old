/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.OrganizationCustomer;

/**
 * organization customer repository
 * 
 * @author brad.wu
 */
public interface OrganizationCustomerRepository extends CrudRepository<OrganizationCustomer, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	OrganizationCustomer findByRoleCode(String roleCode);
}
