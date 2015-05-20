/**
 * 
 */
package com.github.nest.quelea.internal.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.internal.model.Individual;

/**
 * individual repository
 * 
 * @author brad.wu
 */
public interface IndividualRepository extends CrudRepository<Individual, Long> {
	/**
	 * find by given id number and id type code
	 * 
	 * @param idNumber
	 * @param idTypeCode
	 * @return
	 */
	Individual findByIdNumberAndIdTypeCode(String idNumber, String idTypeCode);
}
