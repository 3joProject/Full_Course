package com.fullcourse.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/memberinsert")
	public String insert(Model model) {
		
		log.info("/memberinsert");
		
		model.addAttribute("title", "회원가입");
		
		return "thymeleaf/member/insert";
	}
	
	@PostMapping("/memberinsertOK")
	public String insertOK(MemberVO memberVO) {
		
		log.info("/memberinsertOK...");

		int result = memberService.insertOK(memberVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	
	
}
