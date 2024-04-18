package com.fullcourse.festival.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.festival.FestivalVO;
import com.fullcourse.festival.like.FestivalLikeVO;

@Mapper
public interface FestivalLikeMapper {

	public int festivalLikeInsertOK(FestivalLikeVO vo);
	public int festivalLikeDeleteOK(FestivalLikeVO vo);

}
