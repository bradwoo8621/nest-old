/**
 * 
 */
package com.github.nest.quelea.internal.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.internal.model.Organization;

/**
 * organization repository
 * 
 * @author brad.wu
 */
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	/**
	 * find by given id number and id type code
	 * 
	 * @param idNumber
	 * @param idTypeCode
	 * @return
	 */
	Organization findByIdNumberAndIdTypeCode(String idNumber, String idTypeCode);
}
