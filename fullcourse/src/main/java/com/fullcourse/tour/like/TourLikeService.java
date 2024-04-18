package com.fullcourse.tour.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.tour.TourVO;
import com.fullcourse.tour.mapper.TourLikeMapper;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TourLikeService {

	@Autowired
	private TourLikeMapper mapper;

	public int tourLikeInsertOK(TourLikeVO vo) {
		return mapper.tourLikeInsertOK(vo);
	}

	public int tourLikeDeleteOK(TourLikeVO vo) {
		return mapper.tourLikeDeleteOK(vo);
	}

}
