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
	
	public List<CartVO> selectAll() {

		return mapper.selectAll();
	}

	public int deleteOK(CartVO vo) {
		return mapper.deleteOK(vo);
	}

	public int insertOK(CartVO vo) {
		return mapper.insertOK(vo);
	}

	public int updateOK(CartVO vo) {
		return mapper.updateOK(vo);
	}

	public int chkWDuplCart(CartVO vo) {
		return mapper.chkWDuplCart(vo);
	}



}
