package com.fullcourse.wishlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.cart.mapper.CartMapper;
import com.fullcourse.festival.FestivalVO;
import com.fullcourse.tour.TourVO;
import com.fullcourse.wishlist.mapper.WishlistMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishlistService {

	@Autowired
	private WishlistMapper mapper;
	

	public List<WishlistViewVO> selectAllTour(int tpage, int pageBlock) {
		int startRow = (tpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.selectAllTour(map);
	}

	public List<WishlistViewVO> selectAllFestival(int fpage, int pageBlock) {
		int startRow = (fpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.selectAllFestival(map);
	}

	public int insertOKtour(TourVO vo) {
		return mapper.insertOKtour(vo);
	}

	public int getTotalRowsTour() {
		return mapper.getTotalRowsTour();
	}
	
	public int insertOKfestival(FestivalVO vo) {
		return mapper.insertOKfestival(vo);
	}

	public int getTotalRowsFestival() {
		return mapper.getTotalRowsFestival();
	}

	public int deleteOK(WishlistVO vo) {
		return mapper.deleteOK(vo);
	}

	public int chkWDuplFestival(FestivalVO vo) {
		return mapper.chkWDuplFestival(vo);
	}

	public int chkWDuplTour(TourVO vo) {
		return mapper.chkWDuplTour(vo);
	}
	
	
}
