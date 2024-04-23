package com.fullcourse.wishlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.festival.FestivalVO;
import com.fullcourse.tour.TourVO;
import com.fullcourse.wishlist.mapper.WishlistMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishlistService {

	@Autowired
	private WishlistMapper mapper;
	

	public List<WishlistViewVO> selectAllTour(int tpage, int pageBlock, int wishListId) {
		int startRow = (tpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		map.put("wishListId", wishListId);
		
		return mapper.selectAllTour(map);
	}

	public List<WishlistViewVO> selectAllFestival(int fpage, int pageBlock, int wishListId) {
		int startRow = (fpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		map.put("wishListId", wishListId);
		
		return mapper.selectAllFestival(map);
	}

	public int insertOKtour(WishlistVO vo) {
		return mapper.insertOKtour(vo);
	}

	public int getTotalRowsTour(int wishListId) {
		return mapper.getTotalRowsTour(wishListId);
	}
	
	public int insertOKfestival(WishlistVO vo) {
		return mapper.insertOKfestival(vo);
	}

	public int getTotalRowsFestival(int wishListId) {
		return mapper.getTotalRowsFestival(wishListId);
	}

	public int deleteOK(WishlistVO vo) {
		return mapper.deleteOK(vo);
	}

	public int chkWDuplFestival(WishlistVO vo) {
		return mapper.chkWDuplFestival(vo);
	}

	public int chkWDuplTour(WishlistVO vo) {
		return mapper.chkWDuplTour(vo);
	}
	
	
}
