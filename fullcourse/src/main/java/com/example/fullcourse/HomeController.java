package com.example.fullcourse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		log.info("main...");

		return "thymeleaf/index";
	}
}
