package com.fullcourse.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fullcourse.product.ProductVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
public class CartController {

	@Autowired
	private CartService service;
	
	@GetMapping("/cart_selectAll")
	public String cart_selectAll(Model model) {
		log.info("/cart_selectAll");
		
		List<CartVO> vos = service.selectAll();
		
		log.info("{}",vos);
		
		model.addAttribute("vos",vos);
		
		return "thymeleaf/cart";
	}
	
	@GetMapping("/cart_deleteOK")
	public String cart_deleteOK(CartVO vo) {
		log.info("/cart_deleteOK");
		log.info(vo.toString());
		
		int result = service.deleteOK(vo);
		log.info("result:{}",result);
		
		
		return "redirect:cart_selectAll";
	}
	
	
	
}
