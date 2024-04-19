package com.fullcourse.seller.sellerReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.fullcourse.admin.report.ReportVO;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class SellerReviewController {

	@Autowired
	private SellerReviewService service;
	

	
	@GetMapping("/seller/insertOK")
	public RedirectView sellerReviewInsertOK(SellerReviewVO vo) {
		log.info("sellerReviewInsertOK");
		log.info("vo={}",vo);
		
		int result = service.insertOK(vo);
		log.info("result={}",result);
		
		// 리다이렉트 주소를 동적으로 설정
	    String redirectUrl = "/seller/" + vo.getSelrevId();
	    return new RedirectView(redirectUrl);
	}
	
	@GetMapping("seller/deleteOK")
	public RedirectView sellerReviewDeleteOK(SellerReviewVO vo) {
		log.info("sellerReviewDeleteOK");
		log.info("vo={}",vo);
		
		int result = service.deleteOK(vo);
		log.info("result={}",result);
		
		// 리다이렉트 주소를 동적으로 설정
	    String redirectUrl = "/seller/" + vo.getSelrevId();
	    return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/seller/updateOK")
	public RedirectView sellerReviewUpdateOK(@ModelAttribute("sellerReviewVO") SellerReviewVO vo,
			@RequestParam("sellerId") String sellerId) {
		log.info("sellerReviewUpdateOK");
		log.info("vo={}",vo);
		log.info("sellerId={}",sellerId);
		
		int result = service.updateOK(vo);
		log.info("result={}",result);
		
		// 리다이렉트 주소를 동적으로 설정
	    String redirectUrl = "/seller/" + sellerId;
	    return new RedirectView(redirectUrl);
	}
	
	@GetMapping("/seller/report")
	public RedirectView sellerReviewReport(@ModelAttribute("ReportVO") ReportVO vo,
			@RequestParam("reportReportedId") String sellerId) {
		log.info("sellerReviewReport");
		log.info("vo={}",vo);
		
		int result = service.report(vo);
		log.info("result={}",result);
		
		// 리다이렉트 주소를 동적으로 설정
	    String redirectUrl = "/seller/" + sellerId;
	    return new RedirectView(redirectUrl);
	}
}
