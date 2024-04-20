package com.fullcourse.admin.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.admin.notice.mapper.AdminNoticeMapper;

@Service
public class AdminNoticeService {
	
	@Autowired
	private AdminNoticeMapper mapper;

	
	public List<NoticeVO> selectAllPageBlock(int cpage,int pageBlock) {
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

	public int insertOK(NoticeVO vo) {
		return mapper.insertOK(vo);
	}

	public NoticeVO selectOne(NoticeVO vo) {
		return mapper.selectOne(vo);
	}

	public int updateOK(NoticeVO vo) {
		return mapper.updateOK(vo);
	}

	public int deleteOK(NoticeVO vo) {
		return mapper.deleteOK(vo);
	}

	public NoticeVO update(int noticeNum) {
		// TODO Auto-generated method stub
		return mapper.update(noticeNum);
	}

}
