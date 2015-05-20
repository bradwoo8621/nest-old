/**
 * 
 */
package com.github.nest.quelea.internal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.github.nest.quelea.internal.model.Party;

/**
 * party repository
 * 
 * @author brad.wu
 */
public interface PartyRepository extends CrudRepository<Party, Long> {
	/**
	 * find by given party name
	 * 
	 * @param partyName
	 * @return
	 */
	@Query("select p from Party p where p.partyName = :partyName")
	List<Party> findByPartyName(@Param("partyName") String partyName);

	/**
	 * find by given party name%
	 * 
	 * @param partyName
	 * @return
	 */
	@Query("select p from Party p where p.partyName like :partyName%")
	List<Party> findByPartyNameStartingWith(@Param("partyName") String partyName);

	/**
	 * find by given %party name%
	 * 
	 * @param partyName
	 * @return
	 */
	@Query("select p from Party p where p.partyName like %:partyName%")
	List<Party> findByPartyNameLike(@Param("partyName") String partyName);

	/**
	 * find by given id number
	 * 
	 * @param idNumber
	 * @return
	 */
	@Query("select p from Party p where p.idNumber = :idNumber")
	List<Party> findByIdNumber(@Param("idNumber") String idNumber);

	/**
	 * find by given party name and id number
	 * 
	 * @param partyName
	 * @param idNumber
	 * @return
	 */
	@Query("select p from Party p where p.partyName = :partyName and p.idNumber = :idNumber")
	List<Party> findByPartyNameAndIdNumber(@Param("partyName") String partyName, @Param("idNumber") String idNumber);

	/**
	 * find by given party name% and id number
	 * 
	 * @param partyName
	 * @param idNumber
	 * @return
	 */
	@Query("select p from Party p where p.partyName like :partyName% and p.idNumber = :idNumber")
	List<Party> findByPartyNameStartingWithAndIdNumber(@Param("partyName") String partyName,
			@Param("idNumber") String idNumber);

	/**
	 * find by given %party name% and id number
	 * 
	 * @param partyName
	 * @param idNumber
	 * @return
	 */
	@Query("select p from Party p where p.partyName like %:partyName% and p.idNumber = :idNumber")
	List<Party> findByPartyNameLikeAndIdNumber(@Param("partyName") String partyName, @Param("idNumber") String idNumber);
}
