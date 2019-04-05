/**
 * 
 */
package com.invillia.acme.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.model.Store;
import com.invillia.acme.service.StoreService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Marcelo
 *
 */
@EnableSwagger2
@RestController
@RequestMapping("stores")
public class StoreController extends BaseControllerAb{
	
	@Autowired
	private StoreService storeService;

	@GetMapping
	public List<Store> findAll() {
		return storeService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Store> findById(@PathVariable Long id) {
		
		Optional<Store> storeOptional = storeService.findById(id);
		
		if (!storeOptional.isPresent()) {
			// Caso não exista, retorna o erro 404
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(storeOptional.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Store save(@Valid @RequestBody Store store) {
		
		return storeService.save(store);
	}	
	

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Store update(@PathVariable Long id, @Valid @RequestBody Store store) {
				
		store.setId(id);
		
		return storeService.update(store);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Store> deletar(@PathVariable Long id) {

		storeService.deleteById(id);
		
		return ResponseEntity.noContent().build();		
	}	
	
}
