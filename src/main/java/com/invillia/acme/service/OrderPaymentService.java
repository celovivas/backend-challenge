/**
 * 
 */
package com.invillia.acme.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.enumerator.StatusOrderPaymentEnum;
import com.invillia.acme.model.OrderPayment;
import com.invillia.acme.model.StatusOrder;
import com.invillia.acme.repository.OrderPaymentRepository;

/**
 * @author Marcelo
 *
 */
@Service
public class OrderPaymentService implements BaseService<OrderPayment>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrderPaymentRepository orderRepository;

	@Override
	public List<OrderPayment> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<OrderPayment> findById(Long id) {
		return orderRepository.findById(id);
	}
	
	public Optional<OrderPayment> findByStatus(Integer status) {
		return orderRepository.findByStatus(status);
	}

	@Override
	public OrderPayment save(OrderPayment object) {
		
		// The first status should be PENDING_PAYMENT
		StatusOrder status =  new StatusOrder(StatusOrderPaymentEnum.PENDING_PAYMENT.getId(), 
											StatusOrderPaymentEnum.PENDING_PAYMENT.getName());
		
		object.setStatus(status);
		
		if (object.getOrderPaymentItens() != null) {
			
			object.getOrderPaymentItens().forEach( 
						orderItem -> orderItem.setOrderPayment(object));
			
		}
		
		return orderRepository.save(object);
	}

	@Override
	public OrderPayment update(OrderPayment object) {
		return orderRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
		
	}
}
