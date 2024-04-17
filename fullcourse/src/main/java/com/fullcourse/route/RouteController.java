package com.fullcourse.route;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RouteController {

//	@Value("${tmap.api.key}")
//	private String tmapApiKey;
	
	
	@Autowired
    private RouteService service;
	
	@GetMapping("/")
	public String map() {
		
		log.info("map..");

		
		return "thymeleaf/route/TMAP"; // index.html 파일을 반환
	}

	@GetMapping("/route/list")
	public String tourlist(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model) {
	    List<RouteVO> routes = service.routeSelectAll(cpage, pageBlock);
	    
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
	    
	    model.addAttribute("vos", routes);

		return "thymeleaf/route/list";
	}
	
	@GetMapping("/route/{routeName}")
	public String selectOne(@PathVariable String routeName, Model model) {
		
		List<RouteVO> routes = service.routeSelectOne(routeName);
		
		model.addAttribute("vos", routes);
		
		
		return "thymeleaf/route/selectOne";
		
	}
	

}
