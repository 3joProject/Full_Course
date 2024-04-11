package com.fullcourse.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/insert")
	public String insert(Model model) {
		
		log.info("/insert");
		
		model.addAttribute("content", "thymeleaf/product/th_insert");
		model.addAttribute("title", "상품등록");
		
		return "thymeleaf/product/th_layout_main";
	}
	
	@PostMapping("/insertOK")
	public String insertOK(ProductVO productVO) {
		
		log.info("/insertOK...");

		int result = productService.insertOK(productVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	
		
	@PostMapping("/updateOK")
	public String updateOK(ProductVO productVO) {
		
		int result = productService.updateOK(productVO);
		log.info("result:{}",result);
		
		return "redirect:selectOne?num=" + productVO.getProductNum();
	}
	
	@GetMapping("/delete")
	public String delete(Model model) {
		log.info("/delete...");

		model.addAttribute("content", "thymeleaf/product/delete");
		model.addAttribute("title", "상품삭제");

		return "thymeleaf/product/selectAll";
	}
	
	@PostMapping("/deleteOK")
	public String deleteOK(ProductVO productVO) {

		int result = productService.deleteOK(productVO);
		log.info("result:{}", result);

		return "redirect:selectAll";
	}
	

	
}