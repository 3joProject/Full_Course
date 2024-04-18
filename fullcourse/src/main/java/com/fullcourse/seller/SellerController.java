package com.fullcourse.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SellerController {

	private SellerService sellerService;
	
	@Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/seller/profile/{sellerId}")
    public String getSellerProfile(@PathVariable String sellerId, Model model) {
        model.addAttribute("member", sellerService.getMemberBySellerId(sellerId));
        return "thymeleaf/seller/profile";
    }
}
