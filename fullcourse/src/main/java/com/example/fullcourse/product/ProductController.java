package com.example.fullcourse.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("productVO", new ProductVO());
		return "thymeleaf/product/insert";
	}
	
	@PostMapping("/insertOK")
	public String insertOK(@ModelAttribute ProductVO productVO) {
		productService.insert(productVO);
		return "redirect:/product/selectAll";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("productNum") int productNum, Model model) {
		ProductVO productVO = productService.selectOne(productNum);
		model.addAttribute("productVO", productVO);
		return "product/update";
	}
	
	@PostMapping("/updateOK")
	public String updateOK(@ModelAttribute ProductVO productVO) {
		productService.update(productVO);
		return "redirect:/product/selectAll"; 
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("productNum") int productNum) {
		productService.delete(productNum);
		return "redirect:/product/selectAll";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		List<ProductVO> productList = productService.selectAll();
		model.addAttribute("productList", productList);
		return "product/list";
	}
	
	@GetMapping("/selectOne")
	public String selectOne(@RequestParam("productNum") int productNum, Model model) {
	    ProductVO productVO = productService.selectOne(productNum);
	    model.addAttribute("productVO", productVO);
	    return "product/view";
	}

	@GetMapping("/searchList")
	public String searchList(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword, Model model) {
	    List<ProductVO> productList = productService.searchList(searchType, keyword);
	    model.addAttribute("productList", productList);
	    return "product/list";
	}
	
	
}
