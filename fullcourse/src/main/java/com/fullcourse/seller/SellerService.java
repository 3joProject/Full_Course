package com.fullcourse.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullcourse.member.MemberVO;
import com.fullcourse.seller.mapper.SellerMapper;

@Service
public class SellerService {

	
	private SellerMapper sellerMapper;
	
	@Autowired
    public SellerService(SellerMapper sellerMapper) {
        this.sellerMapper = sellerMapper;
    }

    public MemberVO getMemberBySellerId(String sellerId) {
        return sellerMapper.findMemberBySellerId(sellerId);
    }
	
	
	
}
