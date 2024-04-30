package com.fullcourse.product.productReview.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.product.ProductVO;
import com.fullcourse.product.productReview.ProductReviewVO;

@Mapper
public interface ProductReviewMapper {
	
	public List<ProductReviewVO> prorevSelectAll();

	public int prorevInsertOK(ProductReviewVO vo);

	public int prorevDeleteOK(ProductReviewVO vo);

	public int prorevUpdateOK(ProductReviewVO vo);
	
	
}
