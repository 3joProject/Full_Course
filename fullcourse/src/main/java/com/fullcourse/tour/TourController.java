package com.fullcourse.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.member.MemberVO;

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
	public String tourInsert(Model model) {
		log.info("tourInsert...");

		model.addAttribute("content", "thymeleaf/tour/th_Insert");
		model.addAttribute("title", "여행지입력");
		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지입력 완료 처리 -> tourDeails페이지로 이동?
	@PostMapping("/tour/tourInsertOK")
	public String tourInsertOK(TourVO vo) {
		log.info("tourInsertOK...");
		log.info("vo:{}", vo);

		int result = service.TourInsertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:tourInsert";
		} else {
			return "redirect:tourInsert";
		}
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
	public String tourSelectOne(TourVO vo, Model model) {
		log.info("tourSelectOne ...");
		log.info("vo:{}", vo);

		TourVO vo2 = service.TourSelectOne(vo);

		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/tour/th_selectOne");
		model.addAttribute("title", "회원정보");

		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지 목록
	@GetMapping("/tour/tourSelectAll")
	public String tourSelectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model) {
		log.info("tourSelectAll ...");
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
		
		List<MemberVO> vos = service.tourSelectAll(cpage, pageBlock);
		model.addAttribute("vos", vos);

		// member테이블에 들어있는 모든회원수는 몇명?
		int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);

		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		// 페이지 링크 몇개?
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
//		model.addAttribute("totalPageCount", 10);//테스트용

		model.addAttribute("content", "thymeleaf/tour/th_selectAll");
		model.addAttribute("title", "여행지목록");
		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지 목록 검색
	@GetMapping("/tour/tourSearchList")
	public String tourSearchList() {
		log.info("tourSearchList ...");

		return "thymeleaf/single";
	}

}
