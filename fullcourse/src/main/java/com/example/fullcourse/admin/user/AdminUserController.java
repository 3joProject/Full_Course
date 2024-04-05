package com.example.fullcourse.admin.user;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService service;
	
	@GetMapping("/admin/user/list")
	public String userList(@RequestParam(defaultValue="1")int cpage,
			@RequestParam(defaultValue="10") int pageBlock, Model model ) {
		
		log.info("/usrList");
		log.info("cage : {}, pageBlock : {}", cpage, pageBlock);
		
		List<UserVO> vos = service.userList(cpage, pageBlock);
		
		log.info("User List: {}", vos);
		
		model.addAttribute("vos", vos);
		
		//AdminUser테이블에 들어 있는 모든 회원수는 몇명?
		int total_rows = service.getTotalRows();
		log.info("total_rows:" + total_rows);
		
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		// 페이지 링크 몇개?
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("content", "thymeleaf/adminUser/th_selectAll");
		model.addAttribute("title", "회원목록");
		return "thymeleaf/adminUser/th_selectAll";
		
	}
	
}
