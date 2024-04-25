package com.fullcourse.route;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.route.mapper.RouteMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class RouteService {
	
	
	@Autowired
	private RouteMapper mapper;
	
	public void save(RouteVO vo) {
		mapper.save(vo);
	}
	
	public List<RouteVO> routeSelectAll(int cpage, int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.routeSelectAll(map);
	}
	
	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public List<RouteVO> routeSelectOne(String routeName) {
		// TODO Auto-generated method stub
		return mapper.routeSelectOne(routeName);
	}

	public List<TourVO> getTours() {
		// TODO Auto-generated method stub
		return mapper.getTours();
	}

	public List<RouteVO> getRoute() {
		// TODO Auto-generated method stub
		return mapper.getRoute();
	}

	public List<RouteVO> selectAllRouteList(int cpage, int pageBlock, String routeUserId) {
		// TODO Auto-generated method stub
		
		int startRow = (cpage - 1) * pageBlock + 1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		map.put("routeUserId", routeUserId);
		
		log.info("map: {}", map);


		return mapper.selectAllRouteList(map);
	}

	public int getTotalRowsRouteList(String routeUserId) {
		// TODO Auto-generated method stub
		return mapper.getTotalRowsRouteList(routeUserId);
	}

	public List<RouteVO> routeUpdate(String routeName) {
		// TODO Auto-generated method stub
		return mapper.routeUpdate(routeName);
	}

	public int deleteAllRoutes(String routeName) {
		return mapper.deleteAllRoutes(routeName);
		
	}

	public int mypageDeleteAll(RouteVO vo) {
		// TODO Auto-generated method stub
		return mapper.mypageDeleteAll(vo);
	}
	

}
