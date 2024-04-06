package com.fullcourse.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {

	private int cart_num;
	private int cart_id;
	private int cart_product;
	private int product_count;
	
}
