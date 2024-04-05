package com.example.fullcourse.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullcourse.cart.CartVO;

@Mapper
public interface CartMapper {
	
	public List<CartVO> selectAll();
	
}
