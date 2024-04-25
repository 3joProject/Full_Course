package com.fullcourse.tour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.mapper.TourMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TourService {

	@Autowired
	private TourMapper mapper;

	public TourVO tourSelectOne(TourVO vo) {
		log.info("이거1");
		return mapper.tourSelectOne(vo);
	}

	public int tourInsertOK(TourVO vo) {
		return mapper.tourInsertOK(vo);
	}

	public List<TourVO> tourSelectAll(int cpage, int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.tourSelectAll(map);
	}


	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public int tourDeleteOK(TourVO vo) {
		return mapper.tourDeleteOK(vo);
	}

	public int tourUpdateOK(TourVO vo) {
		return mapper.tourUpdateOK(vo);
	}

	public List<TourVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		
	Map<String, Object> map = new HashMap<>();
	map.put("searchWord", "%"+searchWord+"%");
	
	int startRow = (cpage - 1) * pageBlock + 1;
	map.put("startRow", startRow-1);
	map.put("pageBlock", pageBlock);
	
	if(searchKey.equals("Address")) {
		return mapper.searchListADDRESS_PAGE(map);
	}else {
		return mapper.searchListNAME_PAGE(map);
	}
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("address")) {
			return mapper.search_total_rows_address(map);
		}else {
			return mapper.search_total_rows_tourName(map);
		}
	}

	

	public int updateLikeCount(TourVO vo) {
		return mapper.updateLikeCount(vo);		
	}

	 
	public int updateviewCount(TourVO vo) {
		return mapper.updateViewCount(vo);		
	}

	

	public List<TourVO> searchListPageBlock(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", searchWord+"%");
		return mapper.searchWordaddress(map);
	}

	public List<TourVO> searchListAddressPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", searchWord+"%");
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		return mapper.searchListAddressPageBlock(map);
	}

	
	 // 페이징처리 토탈갯수 반환
    public int selectListTotalCount(TourVO tourVO) throws Exception{
        return mapper.selectListTotalCount(tourVO);
    }
    // 페이징용 리스트 반환
    public List<TourVO> selectTourListWithPaging(TourVO tourVO) throws Exception {
        return mapper.selectTourListWithPaging(tourVO);
    }

    //bestTop3
    public List<TourVO> tourSelectAllTop(TourVO tourVO) {
		return mapper.tourSelectAllTop(tourVO);
	}

    //좋아요 여부확인
	public int getTourLikeCount(int memberNum, int tourNum) {
		return mapper.getTourLikeCount(memberNum,tourNum);
	}

	public List<TourVO> selectTourViewTopListWithPaging(TourVO tourVO) {
		return mapper.selectTourViewTopListWithPaging(tourVO);
	}
}
