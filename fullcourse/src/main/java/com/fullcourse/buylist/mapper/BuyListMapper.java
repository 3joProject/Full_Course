package com.fullcourse.buylist.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;

import com.fullcourse.buylist.BuyListVO;

@Mapper
public interface BuyListMapper {


	@Select("SELECT * FROM buylist WHERE buymnum = #{memberNum}")
    List<BuyListVO> findByMemberNum(int memberNum);
	
	@Select("select * from buylist b, cart c\r\n"
			+ "where b.cartNum = c.cartNum\r\n"
			+ "and c.cartMId = #{cartMid}")
	List<BuyListVO> findByMemberNum2(String cartMid);


}