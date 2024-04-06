package com.fullcourse.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
	private int productNum;
	private int productMnum;
	private String productTitle;
	private int productPrice;
	private String productImage;
	private String productContent;
	private int productInventory;
	

}