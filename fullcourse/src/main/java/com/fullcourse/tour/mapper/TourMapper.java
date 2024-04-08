package com.fullcourse.tour.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.tour.TourVO;

@Mapper
public interface TourMapper {


	public TourVO TourSelectOne(TourVO vo);

}
