package com.fullcourse.admin.adminlist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.admin.AdminVO;

@Mapper
public interface AdminListMapper {

	public List<AdminVO> selectAllPageBlock(Map<String, Integer> map);

	int getTotalRows();

	public int adminDeleteOK();



}
