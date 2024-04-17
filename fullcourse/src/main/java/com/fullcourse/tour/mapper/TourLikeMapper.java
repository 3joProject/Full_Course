package com.fullcourse.tour.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.tour.TourVO;
import com.fullcourse.tour.like.TourLikeVO;

@Mapper
public interface TourLikeMapper {

	public int tourLikeInsertOK(TourLikeVO vo);
	public int tourLikeDeleteOK(TourLikeVO vo);
	public int tourLikeInsertOK2(TourVO vo);

}
