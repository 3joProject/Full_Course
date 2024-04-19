package com.fullcourse.seller.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fullcourse.member.MemberVO;

@Mapper
public interface SellerMapper {

	
    @Select("SELECT m.* FROM member m JOIN seller s ON m.memberId = s.sellerId WHERE s.sellerId = #{sellerId}")
    MemberVO findMemberBySellerId(String sellerId);
}
