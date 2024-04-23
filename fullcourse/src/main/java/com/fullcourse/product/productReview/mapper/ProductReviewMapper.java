package com.fullcourse.product.productReview.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.admin.report.ReportVO;
import com.fullcourse.product.productReview.ProductReviewVO;

@Mapper
public interface ProductReviewMapper {
	
	List<ProductReviewVO> prorevSelectAll();

	int insertOK(ProductReviewVO vo);

	int deleteOK(ProductReviewVO vo);

	int updateOK(ProductReviewVO vo);

	int report(ReportVO vo);
	
}
