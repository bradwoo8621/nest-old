/**
 * 
 */
package com.github.nest.quelea.internal.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.nest.quelea.internal.model.Party;

/**
 * party repository
 * 
 * @author brad.wu
 */
public interface PartyRepository extends CrudRepository<Party, Long> {
}
