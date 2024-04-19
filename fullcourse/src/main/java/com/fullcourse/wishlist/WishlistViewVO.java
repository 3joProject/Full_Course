package com.fullcourse.wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistViewVO {

	private int wishListNum;
	private int wishListId;
	private int tourNum;
	private String tourName;
	private String tourAddress;
	private String tourContent;
	private String tourTel;
	private int festivalNum;
	private String festivalName;
	private String festivalAddress;
	private String festivalContent;
	private String festivalStart;
	private String festivalFinal;
	private String festivalTel;
	
	
}
