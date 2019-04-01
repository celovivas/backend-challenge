/**
 * 
 */
package com.invillia.acme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.model.Store;

/**
 * @author Marcelo
 *
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByName(String name);
}
