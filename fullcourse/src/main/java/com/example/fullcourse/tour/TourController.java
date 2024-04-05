package com.example.fullcourse.tour;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TourController {
	
	@GetMapping("/tour")
	public String m_insert(Model model) {
		log.info("/m_insert...");

		model.addAttribute("content", "thymeleaf/member/th_insert");
		model.addAttribute("title", "회원가입");
		return "thymeleaf/member/th_layout_main";
	}


}
