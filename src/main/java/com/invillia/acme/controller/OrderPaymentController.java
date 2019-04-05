/**
 * 
 */
package com.invillia.acme.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import org.springframework.web.server.ResponseStatusException;

import com.invillia.acme.model.OrderPayment;
import com.invillia.acme.model.Store;
import com.invillia.acme.service.OrderPaymentService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Marcelo
 *
 */
@EnableSwagger2
@RestController
@RequestMapping("orders")
public class OrderPaymentController {
	
	@Autowired
	private OrderPaymentService orderPaymentService;

	@GetMapping
	public List<OrderPayment> findAll() {
		return orderPaymentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderPayment> findById(@PathVariable Long id) {
		
		Optional<OrderPayment> orderPaymentOptional = orderPaymentService.findById(id);
		
		if (!orderPaymentOptional.isPresent()) {
			// Caso não exista, retorna o erro 404
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderPaymentOptional.get());
	}
	
	@PostMapping("/{id_store}")
	@ResponseStatus(HttpStatus.CREATED)
	public OrderPayment save(@PathVariable Long id_store, @RequestBody OrderPayment orderPayment) {
		
		orderPayment.setStore(new Store(id_store));
		
		return orderPaymentService.save(orderPayment);
	}	
	

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public OrderPayment update(@PathVariable Long id, @Valid @RequestBody OrderPayment orderPayment) {
		
		Optional<OrderPayment> orderPaymentOptional = orderPaymentService.findById(id);

		if (!orderPaymentOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					String.format("Order not found.", id));
		}
		
		orderPayment.setId(id);
		
		return orderPaymentService.save(orderPayment);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<OrderPayment> delete(@PathVariable Long id) {

		try {
			orderPaymentService.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					String.format("Order not found.", id));
		}
		
		return ResponseEntity.noContent().build();		
	}	
	
}
