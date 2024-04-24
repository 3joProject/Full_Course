package com.fullcourse.product.productReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.product.productReview.mapper.ProductReviewMapper;

@Service
public class ProductReviewService {
	
	@Autowired
	private ProductReviewMapper mapper;
	
    public int prorevInsertOK(ProductReviewVO vo) {
		return mapper.insertOK(vo);
	}

    public int prorevDeleteOK(ProductReviewVO vo) {
		return mapper.deleteOK(vo);
	}
	
	public List<ProductReviewVO> prorevSelectAll(ProductReviewVO vo) {
		return mapper.prorevSelectAll();
	}
}
