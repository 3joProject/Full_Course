package com.fullcourse.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.cart.mapper.CartMapper;
import com.fullcourse.product.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

	@Autowired
	private CartMapper mapper;
	

	public int insertBuy(String cartNum) {
		log.info("cartNum:{}",cartNum);
		for (String x : cartNum.split("_")) {
			mapper.insertBuy(Integer.parseInt(x));
		}
		
		
		return 1;
	}

	



}
