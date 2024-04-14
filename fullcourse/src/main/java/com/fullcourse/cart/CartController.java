package com.fullcourse.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fullcourse.product.ProductVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class CartController {

	@Autowired
	private CartService service;
	
	@GetMapping("/cart")
	public String cart(Model model) {
		log.info("cart");
		
		List<CartVO> vos = service.selectAll();
		
		log.info("{}",vos);
		
		model.addAttribute("vos",vos);
		
		return "thymeleaf/cart/cartPage";
	}
	
	@GetMapping("/cart/deleteOK")
	public RedirectView deleteOKcart(CartVO vo) {
		log.info("deleteOKcart");
		log.info(vo.toString());
		
		int result = service.deleteOK(vo);
		log.info("result:{}",result);
		
		
		return new RedirectView("/cart");
	}
	
	@PostMapping("/cart/insertOK")
	public RedirectView insertOKcart(CartVO vo) {
		log.info("insertOKcart");
		log.info(vo.toString());

		int result = service.insertOK(vo);
		log.info("result:{}",result);
		
		return new RedirectView("/cart");
	}
	
	@PostMapping("/cart/updateOK")
	public RedirectView updateOKcart(CartVO vo) {
		log.info("updateOKcart");
		log.info(vo.toString());

		int result = service.updateOK(vo);
		log.info("result:{}",result);
		
		
		return new RedirectView("/cart");
	}
	
	
	
}
