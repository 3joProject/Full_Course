package com.fullcourse.route;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RouteController {

//	@Value("${tmap.api.key}")
//	private String tmapApiKey;
	
	
	@GetMapping("/")
	public String map() {
		
		log.info("map..");

		
		return "thymeleaf/tour/TMAP"; // index.html 파일을 반환
	}

	@GetMapping("/map")
	public String getmap() {

		return "kakaoAPI";

	}

}
