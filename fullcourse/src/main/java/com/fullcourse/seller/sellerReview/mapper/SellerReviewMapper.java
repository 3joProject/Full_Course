package com.fullcourse.seller.sellerReview.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.admin.report.ReportVO;
import com.fullcourse.seller.sellerReview.SellerReviewVO;

@Mapper
public interface SellerReviewMapper {

	List<SellerReviewVO> selectAll();

	int insertOK(SellerReviewVO vo);

	int deleteOK(SellerReviewVO vo);

	int updateOK(SellerReviewVO vo);

	int report(ReportVO vo);

}
