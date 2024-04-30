package com.fullcourse.product.productReview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.product.ProductVO;
import com.fullcourse.product.productReview.mapper.ProductReviewMapper;
import com.fullcourse.tour.tourComment.TourCommentService;
import com.fullcourse.tour.tourComment.TourCommentVO;
import com.fullcourse.tour.tourComment.mapper.TourCommentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductReviewService {
	
	@Autowired
	private ProductReviewMapper mapper;

	
	public int prorevInsertOK(ProductReviewVO vo) {
		return mapper.prorevInsertOK(vo);
	}

	public int prorevDeleteOK(ProductReviewVO vo) {
		return mapper.prorevDeleteOK(vo);
	}

	public int prorevUpdateOK(ProductReviewVO vo) {
		return mapper.prorevUpdateOK(vo);
	}
	
	public List<ProductReviewVO> prorevSelectAll() {

		return mapper.prorevSelectAll();
	}
	
}
