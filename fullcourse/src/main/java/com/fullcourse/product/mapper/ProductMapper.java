package com.fullcourse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.product.ProductVO;

@Mapper
public interface ProductMapper {
	void update(ProductVO productVO);
	void delete(int product_num);
	List<ProductVO> seletctAll();
	ProductVO selectOne(int product_num);
	List<ProductVO> searchList(String searchType, String keyword);
	void insert(ProductVO productVO); 
}