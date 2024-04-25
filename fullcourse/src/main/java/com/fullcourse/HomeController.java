package com.fullcourse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping({"/index"})
	public String index(Model model) {
		log.info("main...");

		model.addAttribute("content", "thymeleaf/main/main_banner");
		
		return "thymeleaf/main/th_IndexLayout";
	}
}
