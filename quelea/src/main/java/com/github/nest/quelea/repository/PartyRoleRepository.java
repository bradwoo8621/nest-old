/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.PartyRole;

/**
 * role repository
 * 
 * @author brad.wu
 */
public interface PartyRoleRepository extends CrudRepository<PartyRole, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	PartyRole findByRoleCode(String roleCode);
}
