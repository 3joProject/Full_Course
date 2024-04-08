package com.fullcourse.wishlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.cart.mapper.CartMapper;
import com.fullcourse.wishlist.mapper.WishlistMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WishlistService {

	@Autowired
	private WishlistMapper mapper;
	
	public List<WishlistVO> selectAll() {
		return mapper.selectAll();
	}

	public List<WishlistViewVO> selectAllTour() {
		return mapper.selectAllTour();
	}

	public List<WishlistViewVO> selectAllFestival() {
		return mapper.selectAllFestival();
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

}
