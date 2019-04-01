/**
 * 
 */
package com.invillia.acme.enumerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Marcelo
 *
 */
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum StatusOrderPaymentEnum {
	PENDING_PAYMENT(1l, "Pending Payment"),
	CLOSED(2l, "Closed"),
	CANCELED(3l, "Canceled");
	
	Long id;
	String name;
}
