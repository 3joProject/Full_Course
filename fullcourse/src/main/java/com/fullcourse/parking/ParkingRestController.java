package com.fullcourse.parking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ParkingRestController {

	@Autowired
	private ParkingService service;


	
	@GetMapping("/api/parking")
	public List<ParkingVO>  parking(@RequestParam double lat, @RequestParam double lng) {
		log.info("parking...");
		log.info("lat:{}",lat);
		log.info("lng:{}",lng);

		List<ParkingVO> vos = service.marker(lat, lng);
		log.info("vos:{}",vos);
		
		
		return vos;
	}

}