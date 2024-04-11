package com.fullcourse.seller.sellerReview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerReviewVO {
	
	private int selrevNum;
	private String selrevId;
	private String selrevContent;
	private String selrevDate;
	private String selrevWriter;
	private String selrevStatus;
	
}
