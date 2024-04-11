package com.fullcourse.seller.sellerReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class SellerReviewController {

	@Autowired
	private SellerReviewService service;
	
	@GetMapping("/sellerReview")
	public String sellerReview(Model model) {
		log.info("/sellerReview");
		
		List<SellerReviewVO> vos = service.selectAll();
		log.info("vos:{}",vos);
		
		model.addAttribute("vos",vos);
		
		return "thymeleaf/sellerReview/sellerReviewPage";
	}
	
	@PostMapping("/sellerReview/insertOK")
	public RedirectView sellerReviewInsertOK(SellerReviewVO vo) {
		log.info("sellerReviewInsertOK");
		log.info("vo={}",vo);
		
		int result = service.insertOK(vo);
		log.info("result={}",result);
		
		return new RedirectView("/sellerReview");
	}
	
	@GetMapping("sellerReview/deleteOK")
	public RedirectView sellerReviewDeleteOK(SellerReviewVO vo) {
		log.info("sellerReviewDeleteOK");
		log.info("vo={}",vo);
		
		int result = service.deleteOK(vo);
		log.info("result={}",result);
		
		return new RedirectView("/sellerReview");
	}
	
	@PostMapping("/sellerReview/updateOK")
	public RedirectView sellerReviewUpdateOK(@ModelAttribute("sellerReviewVO") SellerReviewVO vo) {
		log.info("sellerReviewUpdateOK");
		log.info("vo={}",vo);
		
		int result = service.updateOK(vo);
		log.info("result={}",result);
		
		return new RedirectView("/sellerReview");
	}
	
}
