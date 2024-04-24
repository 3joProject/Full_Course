package com.fullcourse.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.parking.ParkingVO;

@Mapper
public interface ParkingMapper {

	List<ParkingVO> marker(double lat, double lng);

	int DBinsert(ParkingVO vo);


}