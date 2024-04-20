package com.fullcourse.buylist;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fullcourse.member.MemberVO;
import java.util.List;

@Controller
@RequestMapping("/buyList")
public class BuyListController {

    private final BuyListService buyListService;

    @Autowired
    public BuyListController(BuyListService buyListService) {
        this.buyListService = buyListService;
    }

    @GetMapping("/list")
    public String listBuyLists(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/login";
        }

        int memberNum = member.getMemberNum(); // memberNum 사용
        List<BuyListVO> buyLists = buyListService.findByMemberNumPaginated(memberNum, page);
        int totalPages = buyListService.getTotalPages(memberNum);

        model.addAttribute("buyLists", buyLists);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "thymeleaf/buyList/list";
    }

    @GetMapping("/{buyNum}")
    public String detailBuyList(@PathVariable("buyNum") int buyNum, Model model) {
        BuyListVO buyList = buyListService.findByBuyNum(buyNum);
        model.addAttribute("buyList", buyList);
        return "buyList/detail";
    }
}