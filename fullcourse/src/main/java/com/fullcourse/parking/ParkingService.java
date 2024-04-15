package com.fullcourse.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.parking.mapper.ParkingMapper;

@Service
public class ParkingService {

	@Autowired
	private ParkingMapper mapper;
}
