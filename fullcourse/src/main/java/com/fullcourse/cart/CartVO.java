package com.fullcourse.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {

	private int cartNum;
	private int cartId;
	private int cartProduct;
	private int productCount;
	
}
