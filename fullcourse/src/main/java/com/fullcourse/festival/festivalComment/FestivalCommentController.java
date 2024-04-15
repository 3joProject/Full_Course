package com.fullcourse.festival.festivalComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fullcourse.festival.festivalComment.FestivalCommentController;
import com.fullcourse.festival.festivalComment.FestivalCommentService;
import com.fullcourse.festival.festivalComment.FestivalCommentVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FestivalCommentController {
	
	@Autowired
	private FestivalCommentService service;
	
	@PostMapping("/festival/festivalCommentInsert")
	public String festivalCommentInsert(FestivalCommentVO vo) {
		log.info("festivalCommentInsert...");
		log.info("vo:{}",vo);

		int result = service.festivalCommentInsertOK(vo);
		log.info("result:{}",result);

		return "redirect:festivalDetails?festivalNum="+vo.getFestivalcoFnum();
	}
	
	@PostMapping("/festival/festivalCommentUpdateOK")
	public String festivalCommentUpdateOK(FestivalCommentVO vo) {
		log.info("festivalCommentUpdateOK...");
		log.info("vo:{}",vo);

		int result = service.festivalCommentUpdateOK(vo);
		log.info("result:{}",result);

		return "redirect:festivalDetails?festivalNum="+vo.getFestivalcoFnum();
	}
	
	@GetMapping("/festival/festivalCommentDeleteOK")
	public String festivalCommentDeleteOK(FestivalCommentVO vo) {
		log.info("festivalCommentDeleteOK...");
		log.info("vo:{}",vo);

		int result = service.festivalCommentDeleteOK(vo);
		log.info("result:{}",result);

		return "redirect:festivalDetails?festivalNum="+vo.getFestivalcoFnum();
	}
	
	//신고 
//	@GetMapping("/festival/festivalCommentReport")
//	public String festivalCommentReport(FestivalCommentVO vo) {
//		log.info("festivalCommentReport...");
//		log.info("vo:{}",vo);
//
//		int result = service.festivalCommentReport(vo);
//		log.info("result:{}",result);
//
//		return "redirect:festivalDetails?festivalNum="+vo.getFestivalcoFnum();
//	}

}
