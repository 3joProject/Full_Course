package com.fullcourse.route;

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
	

}
