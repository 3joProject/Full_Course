package com.fullcourse.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// 로그인 상태 확인 메소드
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("member") != null;
    }
    
    
    @GetMapping("/insert")
    public String insert(Model model, HttpSession session) {
    	if (!isLoggedIn(session)) {
            return "redirect:/login";  // 로그인 페이지 경로를 확인하고 적절히 수정하세요.
        }
		
		model.addAttribute("content", "thymeleaf/product/insert");
		model.addAttribute("title", "상품등록");
		
		return "thymeleaf/product/layout_main";
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