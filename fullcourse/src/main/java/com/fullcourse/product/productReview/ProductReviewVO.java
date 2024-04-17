package com.fullcourse.product.productReview;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewVO {

	private int prorevNum;
	private String prorevWriter;
	private String prorevContent;
	private String prorevDate;
	private String prorevStatus;
	private int prorevPnum;
	
}
