package com.example.fullcourse.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullcourse.cart.mapper.CartMapper;

@Service
public class CartService {

	@Autowired
	private CartMapper mapper;
	
	public List<CartVO> selectAll() {
		return mapper.selectAll();
	}



}
