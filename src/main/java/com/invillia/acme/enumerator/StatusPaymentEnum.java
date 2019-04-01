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
public enum StatusPaymentEnum {
	PAID(1l, "Paid"),
	REFUNDED(2l, "Refunded");
	
	Long id;
	String name;
}