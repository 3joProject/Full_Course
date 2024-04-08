package com.fullcourse.wishlist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.wishlist.WishlistVO;
import com.fullcourse.wishlist.WishlistViewVO;

@Mapper
public interface WishlistMapper {

	List<WishlistVO> selectAll();

	List<WishlistViewVO> selectAllTour();

	List<WishlistViewVO> selectAllFestival();

	int getTotalRows();

}
