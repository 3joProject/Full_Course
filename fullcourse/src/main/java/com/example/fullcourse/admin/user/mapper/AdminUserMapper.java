package com.example.fullcourse.admin.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullcourse.admin.user.UserVO;

@Mapper
public interface AdminUserMapper {
	
	
	//selectAllPageBlock
	public List<UserVO> userList(Map<String, Integer> map);
	
	public int getTotalRows();
	

}
