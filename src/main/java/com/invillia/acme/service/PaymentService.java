/**
 * 
 */
package com.invillia.acme.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.enumerator.StatusPaymentEnum;
import com.invillia.acme.exception.ApplicationException;
import com.invillia.acme.model.Payment;
import com.invillia.acme.model.StatusPayment;
import com.invillia.acme.repository.PaymentRepository;

/**
 * @author Marcelo
 *
 */
@Service
public class PaymentService implements BaseService<Payment>, Serializable{
	
	private static final int NUM_DAYS_LIMIT_REFUND = 10;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public Optional<Payment> findById(Long id) {
		return paymentRepository.findById(id);
	}
	
	public List<Payment> findPaymentsByOrder(Long id_order) {
		
		return paymentRepository.findPaymentsByOrder(id_order);
	}
	
	@Override
	public Payment save(Payment object) {
		
		// The first status should be PAID
		StatusPayment status =  new StatusPayment(StatusPaymentEnum.PAID.getId(), 
											StatusPaymentEnum.PAID.getName());
		
		object.setStatus(status);
		
		return paymentRepository.save(object);
	}

	@Override
	public Payment update(Payment object) {
		return paymentRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	public Payment refund(Payment object) {

		Optional<Payment> paymentDataBase = this.findById(object.getId());
		
		Calendar confirmationDate = Calendar.getInstance();
		confirmationDate.setTime(paymentDataBase.get().getOrderPayment().getConfirmationDate());
		
		Calendar limitDate = Calendar.getInstance();
		limitDate.add(Calendar.DAY_OF_MONTH, - NUM_DAYS_LIMIT_REFUND);
		
		if (paymentDataBase.isPresent()) {
			if (paymentDataBase.get().getStatus().getId().equals(StatusPaymentEnum.PAID.getId())) {
				
				if ( limitDate.before(confirmationDate)) {

					StatusPayment status =  new StatusPayment(StatusPaymentEnum.REFUNDED.getId(), 
														StatusPaymentEnum.REFUNDED.getName());
					
					paymentDataBase.get().setStatus(status);
					
					return paymentRepository.save(paymentDataBase.get());
				}
			}
			else {
				throw new ApplicationException("The payment is already refunded.");

			}
		}
		
		throw new ApplicationException("The payment just can be refunded in 10 days after the confirmation.");
	}	

}
