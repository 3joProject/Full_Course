package com.fullcourse.buylist;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyListVO {

	private int buyNum;
    private int buyMnum;
    private int buyId;
    private int buyInventory;
    private int buyTotalPrice;
    private Date buyDate;
    
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
