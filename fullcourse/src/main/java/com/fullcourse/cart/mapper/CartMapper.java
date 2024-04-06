package com.fullcourse.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.cart.CartVO;
import com.fullcourse.product.ProductVO;

@Mapper
public interface CartMapper {
	
	public List<ProductVO> selectAll();
	
}
