package com.fullcourse.product.productReview.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.admin.report.ReportVO;
import com.fullcourse.product.productReview.ProductReviewVO;

@Mapper
public interface ProductReviewMapper {
	
	public List<ProductReviewVO> prorevSelectAll(int productNum);

	public int prorevInsertOK(ProductReviewVO vo);

	public int prorevDeleteOK(ProductReviewVO vo);

	public int prorevUpdateOK(ProductReviewVO vo);

	int report(ReportVO vo);
	
}
