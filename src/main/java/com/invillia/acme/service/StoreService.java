/**
 * 
 */
package com.invillia.acme.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.invillia.acme.model.Store;
import com.invillia.acme.repository.StoreRepository;

/**
 * @author Marcelo
 *
 */
@Service
public class StoreService implements BaseService<Store>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> findAll() {
		return storeRepository.findAll();
	}

	@Override
	public Optional<Store> findById(Long id) {
		return storeRepository.findById(id);
	}
	
	public Optional<Store> findByName(String name) {
		return storeRepository.findByName(name);
	}

	@Override
	public Store save(Store object) {

		Optional<Store> storeOptional = storeRepository.findByName(object.getName());
		
		// Valida se já existe uma loja com este nome
		if (storeOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"It's already exists a store with this name.");
		}
		
		return storeRepository.save(object);
	}

	@Override
	public Store update(Store object) {
		
		Optional<Store> storeOptional = this.findById(object.getId());

		if (!storeOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					String.format("Store with id %d not found.", object.getId()));
		}
		
		if (storeOptional.isPresent() && 
				!storeOptional.get().getId().equals(object.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"It's already exists a store with this name.");
		}		
		
		return storeRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		
		try {

			Optional<Store> storeOptional = this.findById(id);
			
			if (storeOptional.isPresent()) {
				if (!storeOptional.get().getOrderPayments().isEmpty()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
							String.format("It's is not possible to delete this Store because it has already Orders associated.", id));
				}
			}
			
			storeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					String.format("Store with id %d not found.", id));
		}
	}
}
