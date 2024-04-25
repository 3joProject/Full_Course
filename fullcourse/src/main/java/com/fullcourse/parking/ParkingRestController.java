package com.fullcourse.parking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ParkingRestController {

	@Autowired
	private ParkingService service;
	@Autowired ObjectMapper objectMapper; // Jackson ObjectMapper를 주입

	
	@GetMapping("/api/parking")
	public List<ParkingVO>  parking(@RequestParam double lat, @RequestParam double lng) {
		log.info("parking...");
		log.info("lat:{}",lat);
		log.info("lng:{}",lng);

		List<ParkingVO> vos = service.marker(lat, lng);
		log.info("vos:{}",vos);
		
		
		return vos;
	}
	
	@GetMapping("/api/parkingDB")
	public String parkingDB(ParkingVO vo) {
		log.info("parkingDB...");
		log.info("vo:{}",vo);
		
		if(vo.getPrkPlceEntrcLa()=="" & vo.getPrkPlceEntrcLo()=="") {
			return "좌표없음"; 
		}else {
			int result = service.DBinsert(vo);
			return "DB입력완료"; 
		}
	}

}