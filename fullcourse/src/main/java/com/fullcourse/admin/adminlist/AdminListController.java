package com.fullcourse.admin.adminlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.admin.AdminVO;

import jakarta.servlet.http.HttpSession;
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
			@RequestParam(defaultValue = "5") int pageBlock, Model model,HttpSession session) {
		log.info("/m_selectAll...");
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

////	admin페이지 멤버 삭제 
//	@GetMapping("/admin/adminlist/Delete")
//	public String adminDelete(Model model) {
//		log.info("adminDelete...");
//
//		model.addAttribute("content", "thymeleaf/admin/adminList/th_delete");
//		model.addAttribute("title", "회원삭제");
//		return "thymeleaf/admin/adminList/th_adminlistLayout_main";
//	}
//
////	admin페이지 멤버 삭제
//	@PostMapping("/admin/adminlist/DeleteOK")
//	public String adminDeleteOK(AdminVO vo) {
//		log.info("adminDeleteOK ...");
//		log.info("vo:{}", vo);
//
//		int result = service.adminDeleteOK(vo);
//		
//		log.info("result:{}", result);
//
//		return "redirect:/admin/adminlist/selectAll";
//		
//	}
	

}
