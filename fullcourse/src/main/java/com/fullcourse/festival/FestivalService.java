package com.fullcourse.festival;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.festival.mapper.FestivalMapper;

@Service
public class FestivalService {

	@Autowired
	private FestivalMapper mapper;

	public FestivalVO festivalSelectOne(FestivalVO vo) {

		return mapper.festivalSelectOne(vo);
	}

	public int festivalInsertOK(FestivalVO vo) {
		return mapper.festivalInsertOK(vo);
	}

	public List<FestivalVO> festivalSelectAll(int cpage, int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow - 1);
		map.put("pageBlock", pageBlock);

		return mapper.festivalSelectAll(map);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public int festivalDeleteOK(FestivalVO vo) {
		return mapper.festivalDeleteOK(vo);
	}

	public int festivalUpdateOK(FestivalVO vo) {
		return mapper.festivalUpdateOK(vo);
	}

	public List<FestivalVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {

		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", "%" + searchWord + "%");

		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow - 1);
		map.put("pageBlock", pageBlock);

		if (searchKey.equals("Address")) {
			return mapper.searchListADDRESS_PAGE(map);
		} else {
			return mapper.searchListNAME_PAGE(map);
		}
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%" + searchWord + "%");

		if (searchKey.equals("address")) {
			return mapper.search_total_rows_address(map);
		} else {
			return mapper.search_total_rows_festivalName(map);
		}
	}

	public List<FestivalVO> festivalSelectAllTop() {
		return mapper.festivalSelectAllTop();
	}

	public int updateLikeCount(FestivalVO vo) {
		return mapper.updateLikeCount(vo);
	}

	public int updateviewCount(FestivalVO vo) {
		return mapper.updateViewCount(vo);
	}

	public List<FestivalVO> searchListAddressPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", searchWord + "%");
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow - 1);
		map.put("pageBlock", pageBlock);
		return mapper.searchListAddressPageBlock(map);
	}

	// 페이징처리 토탈갯수 반환
	public int selectListTotalCount(FestivalVO festivalVO) throws Exception {
		return mapper.selectListTotalCount(festivalVO);
	}

	// 페이징용 리스트 반환
	public List<FestivalVO> selectFestivalListWithPaging(FestivalVO festivalVO) throws Exception {
		return mapper.selectFestivalListWithPaging(festivalVO);
	}

	// bestTop3
	public List<FestivalVO> festivalSelectAllTop(FestivalVO festivalVO) throws Exception{
		return mapper.festivalSelectAllTop(festivalVO);
	}

//좋아요 여부 확인 
	public int getFestivalLikeCount(int memberNum, int festivalNum) {
		return mapper.getFestivalLikeCount(memberNum, festivalNum);
	}

	public List<FestivalVO> selectFestivalViewTopListWithPaging(FestivalVO festivalVO) {
		return mapper.selectFestivalViewTopListWithPaging(festivalVO);
	}
}
