package com.fullcourse.buylist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller

public class BuyListController {

	private final BuyListService buyListService;

	@Autowired
	public BuyListController(BuyListService buyListService) {
		this.buyListService = buyListService;
	}

	@GetMapping("/buyList/list")
	public String showBuyList(@RequestParam(defaultValue = "1") int page,HttpSession session, Model model) {
		Integer memberNum = (Integer) session.getAttribute("memberNum");
		if (memberNum == null) {
			return "redirect:/login"; // 로그인 페이지로 리디렉션
		}

		List<BuyListVO> buyList = buyListService.getBuyListByMemberNum(memberNum);
		model.addAttribute("buyList", buyList);
		return "thymeleaf/buyList/list"; // 구매 내역을 보여주는 Thymeleaf 뷰의 경로
	}
}