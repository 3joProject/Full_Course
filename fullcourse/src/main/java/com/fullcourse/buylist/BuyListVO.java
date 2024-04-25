package com.fullcourse.buylist;

import java.sql.Date;

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
    
    private int memberNum;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberAddress;
    private String memberTel;
    private String memberEmail;
    private Date memberDate;
    private String memberImg;
    private String memberSns;
    private int sellerFollow;
    
	private int productNum;
	private String productMid;
	private String productTitle;
	private int productPrice;
	private String productImage;
	private String productContent;
	private int productInventory;
	private int productGuideNum;

}
