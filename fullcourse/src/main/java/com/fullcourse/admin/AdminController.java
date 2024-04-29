package com.fullcourse.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;

	@GetMapping("/admin/login")
	public String adminlogin() {
		return "thymeleaf/admin/login";
	}

	@PostMapping("/admin/login")
	public String loginProcess(String adminId, String adminPw, HttpSession session,
			RedirectAttributes redirectAttributes) {
		log.info("로그인 시도: {}", adminId);
		AdminVO admin = service.login(adminId, adminPw);

		if (admin != null) {
			session.setAttribute("admin", admin);
			log.info("로그인 성공: {}", adminId);
			return "redirect:/admin";
		} else {
			log.error("로그인 실패: {}", adminId);
			redirectAttributes.addFlashAttribute("message", "Invalid ID or Password");
			  return "redirect:/admin/login";
		}
	}
	
	@GetMapping("/admin")
	public String admin(Model model,HttpSession session) {
		
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		if (admin != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + admin.getAdminId());
			model.addAttribute("loginId", admin.getAdminId());
			model.addAttribute("loggedIn", loggedIn);
			model.addAttribute("sidebar","thymeleaf/admin/sidebar");
			model.addAttribute("content", "thymeleaf/admin/notice/th_selectAll");
			return "thymeleaf/admin/th_adminLayout_main";

		} else {

			log.info("로그인한사람이 없습니다");
			 return "redirect:/admin/login";
			
		}
		
		
	
	}
	
	

	
	
}
