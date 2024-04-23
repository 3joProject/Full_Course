package com.fullcourse.product.productReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.board.BoardVO;
import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.tourComment.TourCommentController;
import com.fullcourse.tour.tourComment.TourCommentVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ProductReviewController {
	
	 @Autowired
	 private ProductReviewService productReviewService;
	
	 // 로그인 상태 확인 메소드
	 private boolean isLoggedIn(HttpSession session) {
		 return session.getAttribute("member") != null;
	 	}
	 
	 //댓글 작성
	 @PostMapping("/product/prorevInsert")
		public String prorevInsert(ProductReviewVO vo) {
		
		 log.info("prorevInsert...");
		 log.info("vo:{}",vo);
			
		 int result = productReviewService.prorevInsertOK(vo);
		 log.info("result:{}",result);

		 return "redirect:prorevDetails?productNum="+vo.getProrevPnum();
	}
	    
	@PostMapping("/insertOK")
	public String insertOK(ProductReviewVO vo, HttpSession session) {
			
		MemberVO loggedInMember = (MemberVO) session.getAttribute("member");
		if (loggedInMember == null) {
			return "redirect:/login"; // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트
		}
		    
		vo.setProrevWriter(loggedInMember.getMemberId());
			
		int result = productReviewService.prorevInsertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	
	@PostMapping("/prorevSelectAll")
    public String addReview(ProductReviewVO vo) {
        productReviewService.prorevInsertOK(vo);
        return "redirect:/products/" + vo.getProrevPnum();
    }

	 
}	
