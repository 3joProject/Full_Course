package com.fullcourse.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {

	private int cartNum;
	private String cartMid;
	private int cartProduct;
	private int cartCount;
	private String cartSellerId;
	private String cartTitle;
	private int cartPrice;
	private String cartImage;
	private String cartContent;
	private int cartInventory;
	
}
