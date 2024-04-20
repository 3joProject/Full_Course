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

	    public List<BuyListVO> findByMemberNumPaginated(int memberNum, int page) {
	        int offset = (page - 1) * 10; // 페이지당 10개씩 보여주기
	        return buyListMapper.findByMemberNumPaginated(memberNum, offset, 10);
	    }

	    public BuyListVO findByBuyNum(int buyNum) {
	        return buyListMapper.findByBuyNum(buyNum);
	    }

	    public int getTotalPages(int memberNum) {
	        int totalCount = buyListMapper.getTotalCount(memberNum);
	        return (totalCount + 9) / 10; // 페이지 수 계산
	    }
	}
