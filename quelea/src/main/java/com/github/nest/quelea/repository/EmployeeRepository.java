/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.Employee;

/**
 * Employee repository
 * 
 * @author brad.wu
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	Employee findByRoleCode(String roleCode);
}
