package com.fullcourse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping({ "/index" })
	public String index(Model model, HttpSession session) {
		log.info("main...");

		// 세션에 로그인 정보가 있는지 확인
//		boolean loggedIn = session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");

		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		} else {
			
			log.info("로그인한사람이 없습니다");

		}

		model.addAttribute("content1", "thymeleaf/main/main_banner");
//		model.addAttribute("content", "thymeleaf/product/th_selectAll");
		

		return "thymeleaf/main/th_IndexLayout";
	}
}
