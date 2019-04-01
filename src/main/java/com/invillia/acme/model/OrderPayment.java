/**
 * 
 */
package com.invillia.acme.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Marcelo
 *
 */
@Entity
@Table(name="order_payment")
@lombok.ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(of = { "id" })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderPayment implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	public OrderPayment(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(max=200)
	@Column(name="address")	
	private String address;
	
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "confirmation_date")	
	private Date confirmationDate;
	
	
	@NotNull
	@JoinColumn(name="id_status_order")
	@OneToOne(cascade = CascadeType.DETACH)
	private StatusOrder status;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Store")
	private Store store;

	@OneToMany(mappedBy = "orderPayment",
        	cascade = CascadeType.ALL,
        	orphanRemoval = true
			)
	private List<OrderPaymentItem> orderPaymentItens;	

	
	@OneToMany(mappedBy = "orderPayment",
        	cascade = CascadeType.ALL,
        	orphanRemoval = true
		)
	private List<Payment> payments;
	
	public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setOrderPayment(this);
    }
 
    public void removePayment(Payment payment) {
        payments.remove(payment);
        payment.setOrderPayment(null);
    }	
	
    
	public void addOrderPaymentItem(OrderPaymentItem orderPaymentItem) {
		orderPaymentItens.add(orderPaymentItem);
		orderPaymentItem.setOrderPayment(this);
    }
 
    public void removeOrderPaymentItem(OrderPaymentItem orderPaymentItem) {
    	orderPaymentItens.remove(orderPaymentItem);
    	orderPaymentItem.setOrderPayment(null);
    }    
}
