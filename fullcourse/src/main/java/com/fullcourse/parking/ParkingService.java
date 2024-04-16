package com.fullcourse.parking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.parking.mapper.ParkingMapper;

@Service
public class ParkingService {

	@Autowired
	private ParkingMapper mapper;

	public List<ParkingVO> marker() {
		return mapper.marker();
	}
}