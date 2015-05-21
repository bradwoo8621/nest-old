/**
 * 
 */
package com.github.nest.quelea.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.Bank;

/**
 * bank repository
 * 
 * @author brad.wu
 */
public interface BankRepository extends CrudRepository<Bank, Long> {
	/**
	 * find by given role code
	 * 
	 * @param roleCode
	 * @return
	 */
	Bank findByRoleCode(String roleCode);
}
