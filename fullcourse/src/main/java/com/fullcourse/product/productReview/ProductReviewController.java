package com.fullcourse.product.productReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullcourse.member.MemberService;
import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ProductReviewController {
	
	@Autowired
	private ProductReviewService service;
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/productLogin")
	public String login(@RequestParam String memberId, @RequestParam String memberPw, HttpSession session) {
	    MemberVO member = memberService.login(memberId, memberPw);
	    if (member != null) {
	        session.setAttribute("loggedInUser", member);
	        return "redirect:/product/selectAll";
	    } else {
	        return "login";
	    }
	}
		
	@PostMapping("/product/prorevInsert")
	public String prorevInsert(HttpSession session, ProductReviewVO vo, Model model) {
		MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {

            boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);

        } else {

            log.info("로그인한사람이 없습니다");

        }
	    
	    vo.setProrevWriter(member.getMemberId()); // 로그인된 사용자의 ID를 리뷰 작성자로 설정
	    log.info("prorevInsert...");
	    log.info("vo:{}", vo);

	    int result = service.prorevInsertOK(vo);
	    log.info("result:{}", result);

	    return "redirect:/product/selectOne" + vo.getProrevPnum(); // 상세 페이지로 리다이렉트
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
