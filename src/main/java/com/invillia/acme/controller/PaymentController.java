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

import com.invillia.acme.exception.ApplicationException;
import com.invillia.acme.model.OrderPayment;
import com.invillia.acme.model.Payment;
import com.invillia.acme.service.PaymentService;

/**
 * @author Marcelo
 *
 */
@RestController
@RequestMapping("payments")
public class PaymentController extends BaseControllerAb{
	
	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public List<Payment> findAll() {
		return paymentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Long id) {
		
		Optional<Payment> orderPaymentOptional = paymentService.findById(id);
		
		if (!orderPaymentOptional.isPresent()) {
			// Caso não exista, retorna o erro 404
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderPaymentOptional.get());
	}
	
	@GetMapping("/order/{id_order}")
	public List<Payment> findPaymentsByOrder(@PathVariable Long id_order) {
		
		List<Payment> list = paymentService.findPaymentsByOrder(id_order);
		return list;
	}	
	
	@PostMapping("/order/{id_order}")
	@ResponseStatus(HttpStatus.CREATED)
	public Payment save(@PathVariable Long id_order, @RequestBody Payment payment) {
		
		payment.setOrderPayment(new OrderPayment(id_order));
		
		return paymentService.save(payment);
	}	
	

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Payment update(@PathVariable Long id, @Valid @RequestBody Payment payment) {
		
		Optional<Payment> orderPaymentOptional = paymentService.findById(id);

		if (!orderPaymentOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					String.format("Payment not found.", id));
		}
		
		payment.setId(id);
		
		return paymentService.save(payment);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Payment> delete(@PathVariable Long id) {

		try {
			paymentService.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					String.format("Payment not found.", id));
		}
		
		return ResponseEntity.noContent().build();		
	}
	
	@PostMapping("/refunds/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Payment refund(@PathVariable("id") Long id) {
		
		try {

			Payment payment = new Payment();
			
			payment.setId(id);
			
			return paymentService.refund(payment);

			
		} catch (Exception e) {
			if (e instanceof ApplicationException) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());				
				
			}else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}		
	}		
	
}
