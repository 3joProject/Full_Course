package com.fullcourse.buylist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fullcourse.buylist.BuyListVO;

@Mapper
public interface BuyListMapper {

    @Select("SELECT * FROM buylist WHERE buymnum = #{memberNum}")
    List<BuyListVO> findByMemberNum(int memberNum);

}