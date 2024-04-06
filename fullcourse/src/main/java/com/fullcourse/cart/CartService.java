package com.fullcourse.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.cart.mapper.CartMapper;
import com.fullcourse.product.ProductVO;

@Service
public class CartService {

	@Autowired
	private CartMapper mapper;
	
	public List<ProductVO> selectAll() {

		return mapper.selectAll();
	}



}
