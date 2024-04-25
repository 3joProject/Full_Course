package com.fullcourse.triprecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.triprecord.mapper.TripRecordMapper;

@Service
public class TripRecordService {

	@Autowired
	private TripRecordMapper mapper;

	public List<TripRecordVO> selectAll(int cpage, int pageBlock, int tripRecMnum) {
		int startRow = (cpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		map.put("tripRecMnum", tripRecMnum);
		
		return mapper.selectAll(map);
	}

	public int getTotalRows(int tripRecMnum) {
		return mapper.getTotalRows(tripRecMnum);
	}

	public int insertOK(TripRecordVO vo) {
		return mapper.insertOK(vo);
	}

	public TripRecordVO selectOne(TripRecordVO vo) {
		return mapper.selectOne(vo);
	}

	public int updateOK(TripRecordVO vo) {
		return mapper.updateOK(vo);
	}

	public int deleteOK(TripRecordVO vo) {
		return mapper.deleteOK(vo);
	}

	public List<TripRecordVO> marker(int triprecMnum) {
		return mapper.marker(triprecMnum);
	}
}
