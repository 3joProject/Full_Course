package com.fullcourse.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.cart.CartVO;
import com.fullcourse.product.ProductVO;

@Mapper
public interface CartMapper {
	
	public List<CartVO> selectAll(String memberId);

	public int deleteOK(CartVO vo);

	public int insertOK(CartVO vo);

	public int updateOK(CartVO vo);

	public int chkWDuplCart(CartVO vo);

	public int insertBuy(int cartNum, int memberNum);
	
}
