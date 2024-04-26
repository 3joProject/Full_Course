package com.fullcourse.admin.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.tour.TourService;
import com.fullcourse.tour.TourVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminTourController {
	
	@Autowired
	private TourService service;
	
	@GetMapping("/admin/tour/selectAll")
	public String selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model) {
		log.info("/selectAll...");
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);

		List<TourVO> vos = service.tourSelectAll(cpage, pageBlock);

		model.addAttribute("vos", vos);

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
		model.addAttribute("sidebar","thymeleaf/admin/sidebar");
		model.addAttribute("content", "thymeleaf/admin/tour/th_selectAll");
		model.addAttribute("title", "관리자 여행지 목록");
		return "thymeleaf/admin/th_adminLayout_main";
	}

}
