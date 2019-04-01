/**
 * 
 */
package com.invillia.acme.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Marcelo
 *
 */
@Entity
@Table(name="store")
@lombok.ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(of = { "id" })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Store(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(max=100)
	@Column(name="name")	
	private String name;
	
	@NotEmpty
	@Size(max=200)
	@Column(name="address")	
	private String address;
	
	
	@JsonIgnore // to break endless loop in bi-directional association
	@OneToMany(mappedBy = "store",
        	cascade = CascadeType.DETACH,
        	orphanRemoval = false,
        	fetch = FetchType.LAZY
		)
	private List<OrderPayment> orderPayments;	
	
	
	public void addOrderPayment(OrderPayment orderPayment) {
		orderPayments.add(orderPayment);
		orderPayment.setStore(this);
    }
 
    public void removePayment(OrderPayment orderPayment) {
    	orderPayments.remove(orderPayment);
    	orderPayment.setStore(null);
    }
	
}
