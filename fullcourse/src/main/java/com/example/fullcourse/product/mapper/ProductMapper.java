package com.example.fullcourse.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullcourse.product.ProductVO;

@Mapper
public interface ProductMapper {
	void insert(ProductVO productVO); 
	void update(ProductVO productVO);
	void delete(int product_num);
	List<ProductVO> seletctAll();
	ProductVO selectOne(int product_num);
	List<ProductVO> searchList(String searchType, String keyword);
	int getTotalRows();
}
