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

		return "thymeleaf/blog";
	}
	
	@GetMapping("/tour2")
	public String m_insert2(Model model) {
		log.info("/m_insert...");

		return "thymeleaf/service";
	}

	@GetMapping("/")
	public String index(Model model) {
		log.info("/m_insert...");

		return "thymeleaf/index";
	}

}
