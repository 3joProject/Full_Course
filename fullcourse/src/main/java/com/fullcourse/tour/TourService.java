package com.fullcourse.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.tour.mapper.TourMapper;

@Service
public class TourService {

	@Autowired
	private TourMapper mapper;

	public TourVO TourSelectOne(TourVO vo) {
		
		return mapper.TourSelectOne(vo);
	}
	
	
}
