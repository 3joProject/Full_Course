package com.fullcourse.tour.tourComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TourCommentController {

	@Autowired
	private TourCommentService service;
	
	@PostMapping("/tour/tourCommentInsert")
	public String tourCommnetInsert(TourCommentVO vo) {
		log.info("tourCommnetInsert...");
		log.info("vo:{}",vo);

		int result = service.tourCommentInsertOK(vo);
		log.info("result:{}",result);

		return "redirect:tourDetails?tourNum="+vo.getTourcoTnum();
	}
	
	@PostMapping("/tour/tourCommentUpdateOK")
	public String tourCommentUpdateOK(TourCommentVO vo) {
		log.info("tourCommentUpdateOK...");
		log.info("vo:{}",vo);

		int result = service.tourCommentUpdateOK(vo);
		log.info("result:{}",result);

		return "redirect:tourDetails?tourNum="+vo.getTourcoTnum();
	}
	
	@GetMapping("/tour/tourCommentDeleteOK")
	public String tourCommentDeleteOK(TourCommentVO vo) {
		log.info("tourCommentDeleteOK...");
		log.info("vo:{}",vo);

		int result = service.tourCommentDeleteOK(vo);
		log.info("result:{}",result);

		return "redirect:tourDetails?tourNum="+vo.getTourcoTnum();
	}
	
	//신고 
//	@GetMapping("/tour/tourCommentReport")
//	public String tourCommentReport(TourCommentVO vo) {
//		log.info("tourCommentReport...");
//		log.info("vo:{}",vo);
//
//		int result = service.tourCommentReport(vo);
//		log.info("result:{}",result);
//
//		return "redirect:tourDetails?tourNum="+vo.getTourcoTnum();
//	}
	
}
