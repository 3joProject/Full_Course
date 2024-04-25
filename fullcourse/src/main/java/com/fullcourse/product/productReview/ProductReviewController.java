package com.fullcourse.product.productReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ProductReviewController {
	
	@Autowired
	private ProductReviewService service;
	
	@PostMapping("/product/prorevInsert")
	public String prorevInsert(HttpSession session, ProductReviewVO vo) {
		MemberVO member = (MemberVO) session.getAttribute("loggedInUser");
		 log.info("Session Member: {}", member); // 세션 정보 로깅

		    if (member == null) {
		        log.info("Redirecting to login page");
		        return "redirect:/login"; // 로그인 페이지로 리디렉션
		    }
	    
		log.info("prorevInsert...");
		log.info("vo:{}",vo);

		int result = service.prorevInsertOK(vo);
		log.info("result:{}",result);

		return "redirect:th_selectOne?productNum="+vo.getProrevPnum();
	}
	
	@PostMapping("/product/prorevUpdateOK")
	public String prorevUpdateOK(ProductReviewVO vo) {
		log.info("prorevUpdateOK...");
		log.info("vo:{}",vo);

		int result = service.prorevUpdateOK(vo);
		log.info("result:{}",result);

		return "redirect:th_selectOne?productNum="+vo.getProrevPnum();
	}
	
	@GetMapping("/product/{productNum}/reviews")
	public String getProductReviews(@PathVariable int productNum, Model model) {
	    List<ProductReviewVO> reviews = service.prorevSelectAll(productNum);
	    model.addAttribute("reviews", reviews);
	    return "productDetail"; // 상품 상세 페이지 뷰 이름, 리뷰 목록 포함하여 반환
	}
	 
}	
