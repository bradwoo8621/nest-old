/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.OrganizationAgent;

/**
 * organization agent repository
 * 
 * @author brad.wu
 */
public interface OrganizationAgentRepository extends CrudRepository<OrganizationAgent, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	OrganizationAgent findByRoleCode(String roleCode);
}
