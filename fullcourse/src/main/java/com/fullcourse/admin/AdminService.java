package com.fullcourse.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper mapper;

	public AdminVO login(String adminId, String adminPw) {
		// TODO Auto-generated method stub
		return mapper.login(adminId, adminPw);
	}
	
	

}
