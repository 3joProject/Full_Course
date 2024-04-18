package com.fullcourse.seller.sellerReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.admin.report.ReportVO;
import com.fullcourse.seller.sellerReview.mapper.SellerReviewMapper;

@Service
public class SellerReviewService {

	@Autowired
	private SellerReviewMapper mapper;

	public List<SellerReviewVO> selectAll() {
		return mapper.selectAll();
	}

	public int insertOK(SellerReviewVO vo) {
		return mapper.insertOK(vo);
	}

	public int deleteOK(SellerReviewVO vo) {
		return mapper.deleteOK(vo);
	}

	public int updateOK(SellerReviewVO vo) {
		return mapper.updateOK(vo);
	}

	public int report(ReportVO vo) {
		return mapper.report(vo);
	}
	
}
