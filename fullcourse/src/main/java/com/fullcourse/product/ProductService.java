package com.fullcourse.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.product.mapper.ProductMapper;

@Service
public class ProductService {
	
	
	
	public void insert(ProductVO productVO) {
		
	}
	
	public void update(ProductVO productVO) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int product_num) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ProductVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductVO selectOne(int product_num) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<ProductVO> searchList(String searchType, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
