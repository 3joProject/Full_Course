package com.fullcourse.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductBuyerController {
	
	@Autowired
	private ProductBuyerService service;

	@GetMapping("/selectAll")
	public String selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "9") int pageBlock, Model model) {
		
		log.info("/selectAll");
		
		List<ProductVO> productList = service.selectAllPageBlock(cpage, pageBlock);
		model.addAttribute("productList", productList);
		
		int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);

		
		//product테이블에 있는 상품의 수
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		
		//페이지 링크 개수
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
//		model.addAttribute("content", "thymeleaf/product/th_selectAll");
//		model.addAttribute("title", "상품목록");
		
		return "thymeleaf/product/selectAll";
	}
	
	@GetMapping("/selectOne")
	public String selectOne(ProductVO vo, Model model) {
	    log.info("/selectOne");
	    log.info("vo:{}",vo);
		
		ProductVO vo2 = service.selectOne(vo);
	    
		model.addAttribute("vo2", vo2);
		
		return "thymeleaf/product/selectOne";
	}
	
	@GetMapping("/searchList")
	public String searchList(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, String searchKey, String searchWord, Model model) {
		
		log.info("/searchList");
		log.info("searchKey:{}", searchKey);
		log.info("searchWord:{}", searchWord);
		log.info("cpage:{}, pageBlock:{}", cpage, pageBlock);
		
	    List<ProductVO> productList = service.searchListPageBlock(searchKey, searchWord, cpage, pageBlock);
	    
	    model.addAttribute("productList", productList);
	    
	    int total_rows = service.getSearchTotalRows(searchKey, searchWord);
		log.info("total_rows:" + total_rows);

		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		
		model.addAttribute("totalPageCount", totalPageCount);
		
	    return "thymeleaf/product/selectAll";
	}
	
	@GetMapping("/orderBy")
	public String selectAllOrderBy(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "9") int pageBlock, Model model, String orderBy) {
		
		log.info("/orderBy");
		log.info("orderby:{}",orderBy);
		
		List<ProductVO> productList = service.selectAllOrderBy(cpage, pageBlock, orderBy);
		
		model.addAttribute("productList", productList);
		log.info("productList:{}",productList);
		
		int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);

		
		//product테이블에 있는 상품의 수
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		
		//페이지 링크 개수
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
//		model.addAttribute("content", "thymeleaf/product/th_selectAll");
//		model.addAttribute("title", "상품목록");
		
		return "thymeleaf/product/selectAll";
	}
	
}
