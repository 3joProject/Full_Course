package com.fullcourse.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/route")
public class RouteRestController {
	
	@Autowired
    private RouteService service;
	
	@PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody RouteVO vo) {
		 log.info("Received RouteVO: {}", vo); // VO를 로그에 출력
        try {
        	
        	service.save(vo);
        	
            return ResponseEntity.ok("{\"message\": \"con여행 일정이 저장되었습니다.\"}");
            
        } catch (Exception e) {
        	log.info("not");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"message\": \"con여행 일정 저장에 실패했습니다.\"}");
            
            
        }
    }
	

}
