package com.fullcourse.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ParkingController {

	@Autowired
	private ParkingService service;

	@GetMapping("/parking")
	public String parking() {
		log.info("parking...");

		return "thymeleaf/parking/parking";
	}

}