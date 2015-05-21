/**
 * 
 */
package com.github.nest.quelea.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.model.Party;

/**
 * party repository
 * 
 * @author brad.wu
 */
public interface PartyRepository extends CrudRepository<Party, Long> {
	/**
	 * find by given party code
	 * 
	 * @param partyCode
	 * @return
	 */
	Party findByPartyCode(String partyCode);

	/**
	 * find by given party name
	 * 
	 * @param partyName
	 * @return
	 */
	List<Party> findByPartyName(String partyName);

	/**
	 * find by given party name%
	 * 
	 * @param partyName
	 * @return
	 */
	List<Party> findByPartyNameStartingWith(String partyName);

	/**
	 * find by given party name%
	 * 
	 * @param partyName
	 * @param pageable
	 * @return
	 */
	Page<Party> findByPartyNameStartingWith(String partyName, Pageable pageable);

	/**
	 * find by given %party name%
	 * 
	 * @param partyName
	 * @return
	 */
	@Query("select p from Party p where p.partyName like %?1%")
	List<Party> findByPartyNameLike(String partyName);

	/**
	 * find by given %party name%
	 * 
	 * @param partyName
	 * @param pageable
	 * @return
	 */
	@Query("select p from Party p where p.partyName like %?1%")
	Page<Party> findByPartyNameLike(String partyName, Pageable pageable);

	/**
	 * find by given id number
	 * 
	 * @param idNumber
	 * @return
	 */
	Party findByIdNumber(String idNumber);
}
