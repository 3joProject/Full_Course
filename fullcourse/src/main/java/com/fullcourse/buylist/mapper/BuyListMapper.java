package com.fullcourse.buylist.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;

import com.fullcourse.buylist.BuyListVO;

@Mapper
public interface BuyListMapper {


	@Select("SELECT * FROM buylist WHERE buymnum = #{memberNum}")
    List<BuyListVO> findByMemberNum(int memberNum);
	
	@Select("select * from member m,buylist b,product p,cart c\r\n"
			+ "where m.memberNum = b.buyMnum\r\n"
			+ "and b.buyId = p.productNum\r\n"
			+ "and c.cartNum = b.buyNum\r\n"
			+ "and b.buyMnum = #{memberNum}")
	List<BuyListVO> findByMemberNum2(int memberNum);




}