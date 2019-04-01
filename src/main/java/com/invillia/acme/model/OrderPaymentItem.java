/**
 * 
 */
package com.invillia.acme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Marcelo
 *
 */
@Entity
@Table(name="order_payment_item")
@lombok.ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(of = { "id" })
public class OrderPaymentItem implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotEmpty
	@Size(max=200)
	@Column(name="description")	
	private String description;
	
	
	@NotNull
	@Column(name = "unit_price")	
	private Double unitPrice;

	
	@NotNull
	@Column(name = "quantity")	
	private Double quantity;	
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order_payment")
	private OrderPayment orderPayment;	
}
