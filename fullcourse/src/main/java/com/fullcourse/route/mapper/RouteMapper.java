package com.fullcourse.route.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.route.RouteVO;

@Mapper
public interface RouteMapper {
	
	public void save(RouteVO vo);
	

}
