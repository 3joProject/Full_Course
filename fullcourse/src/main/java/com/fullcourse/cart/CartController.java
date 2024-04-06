package com.fullcourse.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class CartController {

	
	@GetMapping("/cart_selectAll")
	public String cart_selectAll(Model model) {
		log.info("/cart_selectAll");
		
		
		return new String();
	}
	
	
	
}