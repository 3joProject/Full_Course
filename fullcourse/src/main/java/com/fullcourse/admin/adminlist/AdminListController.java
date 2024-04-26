package com.fullcourse.admin.adminlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.admin.AdminVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminListController {
	
	@Autowired
	private AdminListService service;
	
	/*
	@GetMapping("/admin/notice")
	public String notice() {
		return "thymeleaf/admin/notice/selectAll";
	}
	*/
	
	@GetMapping("/admin/adminlist/selectAll")
	public String m_selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model) {
		log.info("/m_selectAll...");
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);

		List<AdminVO> vos = service.selectAllPageBlock(cpage, pageBlock);

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
		model.addAttribute("sidebar","thymeleaf/admin/sidebar");
		model.addAttribute("content", "thymeleaf/admin/adminList/th_selectAll");
	//	model.addAttribute("content", "thymeleaf/admin/adminList/selectAll");
		model.addAttribute("title", "관리자 목록");
//		return "thymeleaf/admin/adminList/selectAll";
		return "thymeleaf/admin/th_adminLayout_main";
	}


}
