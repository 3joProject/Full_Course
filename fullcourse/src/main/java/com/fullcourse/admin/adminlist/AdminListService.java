package com.fullcourse.admin.adminlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.admin.AdminVO;
import com.fullcourse.admin.adminlist.mapper.AdminListMapper;

@Service
public class AdminListService {
	
	@Autowired
	private AdminListMapper mapper;

	
	public List<AdminVO> selectAllPageBlock(int cpage,int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;
		int endRow = startRow + pageBlock - 1;

		Map<String, Integer> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return mapper.selectAllPageBlock(map);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public int adminDeleteOK(AdminVO vo) {
		return mapper.adminDeleteOK();
	}

}
