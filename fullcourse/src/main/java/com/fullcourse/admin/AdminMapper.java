package com.fullcourse.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

	public AdminVO login(String adminId, String adminPw);

}
