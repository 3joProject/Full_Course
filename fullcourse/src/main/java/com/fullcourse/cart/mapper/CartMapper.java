package com.fullcourse.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.cart.CartVO;
import com.fullcourse.product.ProductVO;

@Mapper
public interface CartMapper {
	
	public List<CartVO> selectAll();

	public int deleteOK(CartVO vo);

	public int insertOK(ProductVO vo);

	public int updateOK(CartVO vo);
	
}
