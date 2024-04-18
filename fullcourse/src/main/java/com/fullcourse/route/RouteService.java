package com.fullcourse.route;

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
	

}
