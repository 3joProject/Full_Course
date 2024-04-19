package com.fullcourse.wishlist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.festival.FestivalVO;
import com.fullcourse.tour.TourVO;
import com.fullcourse.wishlist.WishlistVO;
import com.fullcourse.wishlist.WishlistViewVO;

@Mapper
public interface WishlistMapper {

	List<WishlistViewVO> selectAllTour(Map<String, Integer> map);

	List<WishlistViewVO> selectAllFestival(Map<String, Integer> map);

	int insertOKtour(TourVO vo);
	
	int getTotalRowsTour();

	int insertOKfestival(FestivalVO vo);

	int getTotalRowsFestival();

	int deleteOK(WishlistVO vo);

	int chkWDuplFestival(FestivalVO vo);

	int chkWDuplTour(TourVO vo);
	
	

}
