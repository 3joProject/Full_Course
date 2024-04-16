package com.fullcourse.buylist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyList")
public class BuyListController {

    private final BuyListService buyListService;

    @Autowired
    public BuyListController(BuyListService buyListService) {
        this.buyListService = buyListService;
    }

    /// 구매 내역 조회
    @GetMapping
    public String listBuyLists(Model model) {
        List<BuyListVO> buyLists = buyListService.findAll();
        model.addAttribute("buyLists", buyLists);
        return "thymeleaf/buyList/list"; // 구매 내역을 보여주는 뷰 페이지 경로
    }
    @GetMapping("/list")
    public String listBuyListsFallback(Model model) {
        return listBuyLists(model);
    }
    

    // 구매 내역 상세 조회
    @GetMapping("/{buyNum}")
    public String detailBuyList(@PathVariable("buyNum") int buyNum, Model model) {
        BuyListVO buyList = buyListService.findByBuyNum(buyNum);
        model.addAttribute("buyList", buyList);
        return "buyList/detail"; // 구매 내역 상세정보를 보여주는 뷰 페이지 경로
    }

    // 구매 내역 추가 폼
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("buyList", new BuyListVO());
        return "buyList/add"; // 구매 내역 추가 폼 페이지 경로
    }

    // 구매 내역 추가 처리
    @PostMapping("/add")
    public String addBuyList(BuyListVO buyList) {
        buyListService.add(buyList);
        return "redirect:/buyList"; // 추가 후 구매 내역 목록 페이지로 리다이렉트
    }

    // 구매 내역 삭제 처리
    @PostMapping("/delete/{buyNum}")
    public String deleteBuyList(@PathVariable("buyNum") int buyNum) {
        buyListService.delete(buyNum);
        return "redirect:/buyList"; // 삭제 후 구매 내역 목록 페이지로 리다이렉트
    }
}