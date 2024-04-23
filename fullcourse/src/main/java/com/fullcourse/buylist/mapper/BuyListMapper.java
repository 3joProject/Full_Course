package com.fullcourse.buylist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.fullcourse.buylist.BuyListVO;

@Mapper
public interface BuyListMapper {
    List<BuyListVO> findByMemberNumPaginated(int memberNum, int offset, int limit);
    BuyListVO findByBuyNum(int buyNum);
    int getTotalCount(int memberNum);
}