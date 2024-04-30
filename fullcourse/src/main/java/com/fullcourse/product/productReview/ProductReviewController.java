package com.fullcourse.product.productReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fullcourse.member.MemberService;
import com.fullcourse.member.MemberVO;
import com.fullcourse.product.ProductVO;
import com.fullcourse.seller.sellerReview.SellerReviewVO;

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
            return "redirect:/product/th_selectAll";
        } else {
            return "login";
        }
    }

    @PostMapping("/product/prorevInsertOK")
	public RedirectView prorevInsertOK(ProductReviewVO vo, RedirectAttributes attributes) {
		log.info("prorevInsertOK");
		log.info("vo={}",vo);
		
		int result = service.prorevInsertOK(vo);
		log.info("result={}",result);
		
		RedirectView redirectView = new RedirectView();
	    if (result > 0) {
	        // 리뷰 삽입 성공 시, 해당 상품의 상세 페이지로 리다이렉트
	        redirectView.setUrl("/product/selectOne");
	        attributes.addAttribute("productNum", vo.getProrevPnum());
	        log.info("Redirecting to product details page for productNum={}", vo.getProrevPnum());
	    } else {
	        // 리뷰 삽입 실패 시, 오류 페이지 또는 적절한 대체 페이지로 리다이렉트
	        redirectView.setUrl("/error");
	        log.error("Failed to insert product review, redirecting to error page");
	    }
	    return redirectView;
	}

    @GetMapping("/product/prorevDeleteOK")
    public RedirectView prorevDeleteOK(ProductReviewVO vo, RedirectAttributes attributes) {
    	
    	int result = service.prorevDeleteOK(vo);
        log.info("Delete result: {}", result);
        
        RedirectView redirectView = new RedirectView();
        if (result > 0) {
            // 삭제 성공 시, 리뷰 목록 페이지로 리다이렉트
            redirectView.setUrl("/product/selectOne");
            attributes.addAttribute("productNum", vo.getProrevPnum());
            log.info("Successfully deleted product review, redirecting to reviews page");
        } else {
            // 삭제 실패 시, 오류 페이지 또는 적절한 대체 페이지로 리다이렉트
            redirectView.setUrl("/error");
            log.error("Failed to delete product review, redirecting to error page");
        }
        return redirectView;
    }
    

    @PostMapping("/product/prorevUpdate")
    public String updateProductReview(ProductReviewVO productReview) {
        log.info("Updating product review...");
        log.info("ProductReview: {}", productReview);
        int result = service.prorevUpdateOK(productReview);
        log.info("Update result: {}", result);
        return "redirect:/product/th_selectOne?productNum=" + productReview.getProrevPnum();
    }

    private MemberVO checkLoggedInUser(HttpSession session, Model model) {
        MemberVO member = (MemberVO) session.getAttribute("loggedInUser");
        if (member == null) {
            log.info("No logged-in user");
            model.addAttribute("error", "Login required");
        }
        return member;
    }

    
}
	 
	
