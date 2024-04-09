package com.fullcourse.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/insert")
	public String insert(Model model) {
		
		log.info("/insert");
		
		model.addAttribute("content", "thymeleaf/product/th_insert");
		model.addAttribute("title", "상품등록");
		
		return "thymeleaf/product/th_layout_main";
	}
	
	@PostMapping("/insertOK")
	public String insertOK(ProductVO productVO) {
		
		log.info("/insertOK...");

		int result = productService.insertOK(productVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	
		
	@PostMapping("/updateOK")
	public String updateOK(ProductVO productVO) {
		
		int result = productService.updateOK(productVO);
		log.info("result:{}",result);
		
		return "redirect:selectOne?num=" + productVO.getProductNum();
	}
	
	@GetMapping("/delete")
	public String delete(Model model) {
		log.info("/delete...");

		model.addAttribute("content", "thymeleaf/product/delete");
		model.addAttribute("title", "상품삭제");

		return "thymeleaf/product/selectAll";
	}
	
	@PostMapping("/deleteOK")
	public String deleteOK(ProductVO productVO) {

		int result = productService.deleteOK(productVO);
		log.info("result:{}", result);

		return "redirect:selectAll";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model) {
		
		log.info("/selectAll");
		
		List<ProductVO> productList = productService.selectAllPageBlock(cpage, pageBlock);
		model.addAttribute("productList", productList);
		
		int total_rows = productService.getTotalRows();
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
		model.addAttribute("content", "thymeleaf/product/th_selectAll");
		model.addAttribute("title", "상품목록");
		
		return "thymeleaf/product/th_layout_main";
	}
	
	@GetMapping("/selectOne")
	public String selectOne(ProductVO productVO, Model model) {
	    
		ProductVO vo2 = productService.selectOne(productVO);
	    
		model.addAttribute("vo2", vo2);
		
		model.addAttribute("content", "thymeleaf/product/th_selectOne");
		model.addAttribute("title", "상품정보 및 변경");
	
		return "thymeleaf/product/th_layout_main";
	}
	
	@GetMapping("/searchList")
	public String searchList(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, String searchKey, String searchWord, Model model) {
		
		log.info("searchKey:{}", searchKey);
		log.info("searchWord:{}", searchWord);
		log.info("cpage:{}, pageBlock:{}", cpage, pageBlock);
		
	    List<ProductVO> productList = productService.searchListPageBlock(searchKey, searchWord, cpage, pageBlock);
	    
	    model.addAttribute("productList", productList);
	    
	    int total_rows = productService.getSearchTotalRows(searchKey, searchWord);
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
		
		model.addAttribute("content", "thymeleaf/product/selectAll");
		model.addAttribute("title", "상품목록");
		
	    return "thymeleaf/product/selectAll";
	}
	
	
}