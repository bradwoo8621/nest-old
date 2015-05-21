/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.Department;

/**
 * Department repository
 * 
 * @author brad.wu
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	Department findByRoleCode(String roleCode);
}
