package com.fullcourse.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.fullcourse.member.MemberVO;
import com.fullcourse.product.productReview.ProductReviewVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductBuyerController {

	@Autowired
	private ProductBuyerService service;

	@GetMapping("/selectAll")
	public String selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "6") int pageBlock, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		} else {

			log.info("로그인한사람이 없습니다");
		}

		log.info("MemberVO:{}", member);
		model.addAttribute("member", member);

		log.info("/selectAll");

		List<ProductVO> productList = service.selectAllPageBlock(cpage, pageBlock);
		model.addAttribute("productList", productList);

		int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);

		// product테이블에 있는 상품의 수
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}

		// 페이지 링크 개수
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
//		model.addAttribute("content", "thymeleaf/product/th_selectAll");
//		model.addAttribute("title", "상품목록");

		model.addAttribute("content", "thymeleaf/product/th_selectAll");
		// model.addAttribute("title","여행상품쇼핑");

		return "thymeleaf/product/th_layout_main";
	}

	@GetMapping("/selectOne")
	public String selectOne(ProductVO vo, Model model, HttpSession session) {
		log.info("/selectOne");
		log.info("vo:{}", vo);

		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		} else {

			log.info("로그인한사람이 없습니다");
		}

		ProductVO vo2 = service.selectOne(vo);
		log.info("vo2:{}", vo2);
		model.addAttribute("vo2", vo2);

		List<ProductReviewVO> vos = service.productReview(vo);

		model.addAttribute("vos", vos);
		log.info("vos:{}", vos);
		model.addAttribute("content", "thymeleaf/product/th_selectOne");
		model.addAttribute("title", "여행상품 상세보기");

		return "thymeleaf/product/th_layout_main";
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
		model.addAttribute("content", "thymeleaf/product/th_selectAll");
		model.addAttribute("title", "여행상품쇼핑");

		return "thymeleaf/product/th_layout_main";
	}

	@GetMapping("/orderBy")
	public String selectAllOrderBy(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "9") int pageBlock, Model model, String orderBy, HttpSession session) {

		log.info("/orderBy");
		log.info("orderby:{}", orderBy);

		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		} else {

			log.info("로그인한사람이 없습니다");
		}

		List<ProductVO> productList = service.selectAllOrderBy(cpage, pageBlock, orderBy);

		model.addAttribute("productList", productList);
		log.info("productList:{}", productList);

		int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);

		// product테이블에 있는 상품의 수
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}

		// 페이지 링크 개수
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("content", "thymeleaf/product/th_selectAll");
		model.addAttribute("title", "여행상품쇼핑");

		return "thymeleaf/product/th_layout_main";
	}

}
