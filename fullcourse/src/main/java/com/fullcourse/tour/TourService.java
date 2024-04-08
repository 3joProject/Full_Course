package com.fullcourse.tour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.mapper.TourMapper;

@Service
public class TourService {

	@Autowired
	private TourMapper mapper;

	public TourVO TourSelectOne(TourVO vo) {
		
		return mapper.TourSelectOne(vo);
	}

	public int TourInsertOK(TourVO vo) {
		return mapper.TourInsertOK(vo);
	}

	public List<MemberVO> tourSelectAll(int cpage, int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.TourSelectAll(map);
	}


	public int getTotalRows() {
		return mapper.getTotalRows();
	}
	
	
}
