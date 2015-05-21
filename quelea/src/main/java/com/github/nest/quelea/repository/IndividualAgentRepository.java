/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.IndividualAgent;

/**
 * individual agent repository
 * 
 * @author brad.wu
 */
public interface IndividualAgentRepository extends CrudRepository<IndividualAgent, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	IndividualAgent findByRoleCode(String roleCode);
}
