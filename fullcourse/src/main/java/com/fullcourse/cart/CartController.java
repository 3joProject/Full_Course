package com.fullcourse.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {

	@Autowired
	private CartService service;
	
	@GetMapping("/cart")
	public String cart(Model model, HttpServletRequest request) {
		log.info("cart");
			
	    // 세션에서 memberNum 가져오기
	    HttpSession session = request.getSession();
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    log.info("MemberVO:{}",member);
	    
	    
	    if(member == null) {
	        return "redirect:/login";
	    }else {
		    log.info("memberNum: {}", member.toString());

			List<CartVO> vos = service.selectAll(member.getMemberId());
			log.info("{}",vos);
			
			model.addAttribute("vos",vos);
			model.addAttribute("content","thymeleaf/cart/th_cartMain");
			model.addAttribute("title","장바구니");
			
			boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);
			
			return "thymeleaf/cart/th_cartLayout_main";
	    }
	    

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
	public RedirectView insertOKcart(CartVO vo, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		log.info("insertOKcart");
		log.info(vo.toString());
		
		HttpSession session = request.getSession();
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    
	    if(member == null) {
	        return new RedirectView("/login");
	    }else {
	    
	    log.info("MemberVO:{}",member.toString());
	    
	    vo.setCartMid(member.getMemberId());
	    log.info(vo.toString());
	    
		int chkWDuplCart = service.chkWDuplCart(vo);
		log.info("chkWDuplCart:{}",chkWDuplCart);
		
		if(chkWDuplCart > 0) {
			redirectAttributes.addFlashAttribute("warningMessageCart", "이미 추가된 상품입니다."); // 경고 메시지 설정
		}else {
			int result = service.insertOK(vo);
			log.info("result:{}",result);
		}
		return new RedirectView("/cart");
	    }

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
