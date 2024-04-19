package com.fullcourse.parking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.parking.mapper.ParkingMapper;

@Service
public class ParkingService {

	@Autowired
	private ParkingMapper mapper;

	public List<ParkingVO> marker(double lat, double lng) {
		return mapper.marker(lat, lng);
	}
}