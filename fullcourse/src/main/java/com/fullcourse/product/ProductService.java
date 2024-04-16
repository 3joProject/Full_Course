package com.fullcourse.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullcourse.product.mapper.ProductMapper;


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

}
