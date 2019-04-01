/**
 * 
 */
package com.invillia.acme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.invillia.acme.model.Payment;

/**
 * @author Marcelo
 *
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findByStatus(Integer status);
	
	@Query("SELECT p FROM Payment p WHERE p.orderPayment.id = :id_order")
	public List<Payment> findPaymentsByOrder(@Param("id_order") Long id_order);
}
