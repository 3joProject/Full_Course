package com.fullcourse.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullcourse.product.mapper.ProductMapper;
import com.fullcourse.route.RouteVO;
import com.fullcourse.board.BoardVO;
import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;


@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	

	
	public int insertOK(ProductVO productVO) {
		
		return productMapper.insertOK(productVO);
	}
	
	public int updateOK(ProductVO productVO) {
		return productMapper.updateOK(productVO);
		
	}
	
	public int deleteOK(ProductVO productVO) {
		return productMapper.deleteOK(productVO);
		
	}
	
	
	public ProductVO selectProductById(int productNum) {
		return productMapper.selectProductById(productNum);
	}

	public List<ProductVO> sellListSelectAll(String productMid) {
		return productMapper.sellListSelectAll(productMid);
	}

	public List<RouteVO> findAllRoutes(String memberId) {
		// TODO Auto-generated method stub
		return productMapper.findAllRoutes(memberId);
	}
}
