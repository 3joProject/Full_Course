package com.fullcourse.tour;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TourController {
	
	@GetMapping("/tour")
	public String m_insert() {
		log.info("/m_insert...");

		return "thymeleaf/blog";
	}
	
	@GetMapping("/tour/details")
	public String tour_details() {
		log.info("insert...");

		return "thymeleaf/single";
	}

	

}
