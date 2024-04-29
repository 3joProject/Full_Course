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

import com.fullcourse.member.MemberService;
import com.fullcourse.member.MemberVO;
import com.fullcourse.product.ProductVO;

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

    @PostMapping("/product/prorevInsert")
    public String insertProductReview(HttpSession session, ProductReviewVO productReview, Model model, ProductVO product) {
        MemberVO member = checkLoggedInUser(session, model);
        if (member == null) {
            return "login";
        }

        productReview.setProrevWriter(member.getMemberId());
        log.info("Inserting product review...");
        log.info("ProductReview: {}", productReview);

        return prorevInsertOK(productReview, product);
    }

    @PostMapping("/product/prorevInsertOK")
    public String prorevInsertOK(ProductReviewVO productReview, ProductVO product) {
        log.info("Inserting product review in database...");
        try {
            int result = service.prorevInsertOK(productReview);
            log.info("Insert result: {}", result);
            return "redirect:/product/th_selectOne?productNum=" + product.getProductNum();
        } catch (Exception e) {
            log.error("Error inserting review: {}", e.getMessage());
            return "error";  // Assuming there is an 'error' view to handle this
        }
    }

    @PostMapping("/product/prorevDeleteOK")
    public String prorevDeleteOK(ProductReviewVO productReview) {

    	int result = service.prorevDeleteOK(productReview);
        log.info("Delete result: {}", result);
        
        return "redirect:/product/reviews";
    }
    

    @PostMapping("/product/prorevUpdate")
    public String updateProductReview(ProductReviewVO productReview) {
        log.info("Updating product review...");
        log.info("ProductReview: {}", productReview);
        int result = service.prorevUpdateOK(productReview);
        log.info("Update result: {}", result);
        return "redirect:/product/th_selectOne?productNum=" + productReview.getProrevPnum();
    }

    @GetMapping("/product/{productNum}/reviews")
    public String getProductReviews(@PathVariable int productNum, Model model) {
        List<ProductReviewVO> reviews = service.prorevSelectAll(productNum);
        model.addAttribute("reviews", reviews);
        return "productDetail";
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
	 
	
