package com.fullcourse.product.productReview;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewVO {

	private int prorevNum;         // 리뷰 고유 번호
    private String prorevWriter;    // 리뷰 작성자 (Member의 memberId)
    private String prorevContent;   // 리뷰 내용
    private Date prorevDate; // 리뷰 작성 일시
    private int prorevPnum; //리뷰가 작성된 상품 넘버(Product의 productNum)
	
}
