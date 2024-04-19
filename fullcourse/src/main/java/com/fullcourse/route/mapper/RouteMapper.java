package com.fullcourse.route.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.route.RouteVO;
import com.fullcourse.route.TourVO;

@Mapper
public interface RouteMapper {
	
	List<RouteVO> getRoute = null;

	public void save(RouteVO vo);
	
	public void getAllRoutes(RouteVO vo);

	public List<RouteVO> routeSelectAll(Map<String, Integer> map);

	public int getTotalRows();

	public List<RouteVO> routeSelectOne(String routeName);

	public List<TourVO> getTours();

	public List<RouteVO> getRoute();
	
}
