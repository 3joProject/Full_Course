package com.fullcourse.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TourController {
	
	@Autowired
	private TourService service;


	// 여행 페이지 메인
	@GetMapping("/tour")
	public String tourMain(TourVO vo, Model model) {
		log.info("/tourMain...");
		log.info("vo:{}", vo);

//		TourVO vo2 = service.TourSelectOne(vo);
//		TourVO vo3 = service.TourSelectOne(vo);
//		TourVO vo4 = service.TourSelectOne(vo);
//		
//		model.addAttribute("vo2", vo2);
//		model.addAttribute("vo3", vo3);
//		model.addAttribute("vo4", vo4);
		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 상세정보로 이동
	@GetMapping("/tour/tourDetails")
	public String tourDetails(TourVO vo, Model model) {
		log.info("tourDetails...");
		log.info("vo:{}", vo);
		
		TourVO vo2 = service.TourSelectOne(vo);

		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/tour/th_selectOne");
		model.addAttribute("title", "실험");
		

		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지 입력페이지로 이동
	@GetMapping("/tour/tourInsert")
	public String tourInsert() {
		log.info("tourInsert...");

		return "thymeleaf/single";
	}

	// 여행지입력 완료 처리 -> tourDeails페이지로 이동?
	@GetMapping("/tour/tourInsertOK")
	public String tourInsertOK() {
		log.info("tourInsertOK...");

		return "thymeleaf/single";
	}

	// 여행지 정보 수정 페이지 이동
	@GetMapping("/tour/tourUpdate ")
	public String tourUpdate() {
		log.info("tourUpdate ...");

		return "thymeleaf/single";
	}

	// 여행지 정보 수정 완료처리 -> tourDeails페이지로 이동?
	@GetMapping("/tour/tourUpdateOK ")
	public String tourUpdateOK() {
		log.info("tourUpdateOK ...");

		return "thymeleaf/single";
	}

	// 여행지 정보 삭제 페이지
	@GetMapping("/tour/tourDelete")
	public String tourDelete() {
		log.info("tourDelete...");

		return "thymeleaf/single";
	}

	// 여행지 정보 삭제완료 처리 -> selectAll로 돌아가기
	@GetMapping("/tour/tourDeleteOK ")
	public String tourDeleteOK() {
		log.info("tourDeleteOK ...");

		return "thymeleaf/single";
	}

	// 여행지 정보 검색 ?details랑 겹치나 확인
	@GetMapping("/tour/tourSelectOne")
	public String tourSelectOne() {
		log.info("tourSelectOne ...");

		return "thymeleaf/single";
	}

	// 여행지 목록
	@GetMapping("/tour/tourSelectAll")
	public String tourSelectAll() {
		log.info("tourSelectAll ...");

		return "thymeleaf/single";
	}

	// 여행지 목록 검색
	@GetMapping("/tour/tourSearchList")
	public String tourSearchList() {
		log.info("tourSearchList ...");

		return "thymeleaf/single";
	}

}
