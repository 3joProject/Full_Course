package com.fullcourse.buylist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.buylist.mapper.BuyListMapper;

@Service
public class BuyListService {

    private final BuyListMapper buyListMapper;

    @Autowired
    public BuyListService(BuyListMapper buyListMapper) {
        this.buyListMapper = buyListMapper;
    }

    public List<BuyListVO> getBuyListByMemberNum(String cartMid) {
//        return buyListMapper.findByMemberNum(memberNum);
        return buyListMapper.findByMemberNum2(cartMid);
    }


    
    
}
