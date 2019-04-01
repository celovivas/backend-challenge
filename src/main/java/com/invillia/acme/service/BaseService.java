/**
 * 
 */
package com.invillia.acme.service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcelo
 *
 */
public interface BaseService<T>{
	

	public List<T> findAll();
	
	public Optional<T> findById(Long id);
	
	public T save(T object);
	
	public T update(T object);
	
	public void deleteById(Long id);	
}
