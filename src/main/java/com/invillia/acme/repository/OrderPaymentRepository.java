/**
 * 
 */
package com.invillia.acme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.model.OrderPayment;

/**
 * @author Marcelo
 *
 */
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {

	Optional<OrderPayment> findByStatus(Integer status);
}
