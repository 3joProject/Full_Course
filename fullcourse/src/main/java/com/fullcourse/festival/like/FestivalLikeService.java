package com.fullcourse.festival.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.festival.mapper.FestivalLikeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FestivalLikeService {

	@Autowired
	private FestivalLikeMapper mapper;

	public int festivalLikeInsertOK(FestivalLikeVO vo) {
		return mapper.festivalLikeInsertOK(vo);
	}

	public int festivalLikeDeleteOK(FestivalLikeVO vo) {
		return mapper.festivalLikeDeleteOK(vo);
	}

}
