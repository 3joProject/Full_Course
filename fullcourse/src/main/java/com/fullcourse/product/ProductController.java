package com.fullcourse.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullcourse.member.MemberVO;
import com.fullcourse.route.RouteVO;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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
		List<RouteVO> routes = productService.findAllRoutes();  // 경로 데이터를 불러오는 서비스 메소드 호출
	    model.addAttribute("routes", routes);
		
		return "thymeleaf/product/layout_main";
	}
	
	@PostMapping("/insertOK")
	public String insertOK(ProductVO productVO, HttpSession session) {
		
		MemberVO loggedInMember = (MemberVO) session.getAttribute("member");
	    if (loggedInMember == null) {
	        return "redirect:/login"; // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트
	    }
	    
		productVO.setProductMid(loggedInMember.getMemberId());
		
		int result = productService.insertOK(productVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	
	
	private boolean isOwner(int productNum, HttpSession session) {
		ProductVO product = productService.selectProductById(productNum);
		MemberVO currentUser = (MemberVO) session.getAttribute("member");
		return product != null && currentUser != null && product.getProductMid().equals(currentUser.getMemberId());
		
	}
	
	@PostMapping("/updateOK")
	public String updateOK(ProductVO productVO, HttpSession session) {
		if (!isOwner(productVO.getProductNum(), session)) {
            return "redirect:/login";
        }
		
		int result = productService.updateOK(productVO);
		log.info("result:{}",result);
		
		return "redirect:selectOne?num=" + productVO.getProductNum();
	}
	
	
	@PostMapping("/deleteOK")
    public String deleteOK(ProductVO productVO, HttpSession session) {
        if (!isOwner(productVO.getProductNum(), session)) {
            return "redirect:/login";  // Not the owner or not logged in
        }

        int result = productService.deleteOK(productVO);
        log.info("Delete result: {}", result);

        return "redirect:selectAll";
    }
	

	
}