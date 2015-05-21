/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.Branch;

/**
 * Branch repository
 * 
 * @author brad.wu
 */
public interface BranchRepository extends CrudRepository<Branch, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	Branch findByRoleCode(String roleCode);
}
