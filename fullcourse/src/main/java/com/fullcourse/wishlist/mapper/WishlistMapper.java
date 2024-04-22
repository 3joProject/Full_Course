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

	int insertOKtour(WishlistVO vo);
	
	int getTotalRowsTour(int wishListId);

	int insertOKfestival(WishlistVO vo);

	int getTotalRowsFestival(int wishListId);

	int deleteOK(WishlistVO vo);

	int chkWDuplFestival(WishlistVO vo);

	int chkWDuplTour(WishlistVO vo);
	
	

}
