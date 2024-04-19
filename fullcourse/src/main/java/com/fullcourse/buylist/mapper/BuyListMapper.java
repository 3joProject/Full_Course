package com.fullcourse.buylist.mapper;

import java.util.List;

import com.fullcourse.buylist.BuyListVO;

public interface BuyListMapper {
    List<BuyListVO> findAll();
    BuyListVO findByBuyNum(int buyNum);
    void insert(BuyListVO buyList);
    void delete(int buyNum);
}