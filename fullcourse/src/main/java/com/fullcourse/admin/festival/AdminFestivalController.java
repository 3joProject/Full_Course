package com.fullcourse.admin.festival;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.admin.AdminVO;
import com.fullcourse.festival.FestivalService;
import com.fullcourse.festival.FestivalVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminFestivalController {
	
	@Autowired
	private FestivalService service;
	
	@GetMapping("/admin/festival/selectAll")
	public String selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model,HttpSession session) {
		log.info("/selectAll...");
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
		
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		if (admin != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + admin.getAdminId());
			model.addAttribute("loginId", admin.getAdminId());
			model.addAttribute("loggedIn", loggedIn);

		} else {

			log.info("로그인한사람이 없습니다");
			 return "redirect:/admin/login";
		}
		
		List<FestivalVO> vos = service.festivalSelectAll(cpage, pageBlock);

		model.addAttribute("vos", vos);

		// 테이블에 들어있는 모든회원수는 몇명?
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
		model.addAttribute("content", "thymeleaf/admin/festival/th_selectAll");
		model.addAttribute("title", "관리자 축제 목록");
		return "thymeleaf/admin/th_adminLayout_main";
	}

}
