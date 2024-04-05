package com.example.fullcourse.admin.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullcourse.admin.user.mapper.AdminUserMapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminUserService {
	
	
	@Autowired
	private AdminUserMapper mapper;
	
	//selectAllPageBlock
	public List<UserVO> userList(int cpage,int pageBlock) {
		
		log.info("service userList");
		int startRow = (cpage - 1) * pageBlock + 1;
		int endRow = startRow + pageBlock - 1;

		Map<String, Integer> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		log.info("map: {}", map);

		return mapper.userList(map);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	
}
