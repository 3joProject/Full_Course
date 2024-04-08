package com.fullcourse.wishlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class WishlistController {

	@Autowired
	private WishlistService service;
	
	@GetMapping("/wishList")
	public String wishList(@RequestParam(name="cpage",defaultValue = "1") int cpage,
			@RequestParam(name="pageBlock",defaultValue = "6") int pageBlock, Model model) {
		log.info("wishList");
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
		
		List<WishlistVO> vos = service.selectAll();
		
		log.info("vos:{}",vos);
		
		List<WishlistViewVO> vosTour = service.selectAllTour();
		
		log.info("vosTour:{}",vosTour);
		
		List<WishlistViewVO> vosFestival = service.selectAllFestival();
		
		log.info("vosFestival:{}",vosFestival);
		
		List<WishlistViewVO> vosAll = new ArrayList<>();
		vosAll.addAll(vosTour);
		vosAll.addAll(vosFestival);
		
		log.info("vosAll:{}",vosAll);
		
		try {
			vosAll = vosAll.stream().sorted(Comparator.comparing(WishlistViewVO::getWishListNum).reversed()).
					collect(Collectors.toList()).subList((cpage-1)*pageBlock, pageBlock);
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		log.info("vosAll2:{}",vosAll);
		
		model.addAttribute("vosAll",vosAll);
		
		int total_rows = service.getTotalRows();
		log.info("total_rows:{}", total_rows);
		
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		
		log.info("totalPageCount:{} ", totalPageCount);
		
		model.addAttribute("totalPageCount", totalPageCount);

		
	
		return "thymeleaf/wishList/wishListPage";
	}
	
	
}
