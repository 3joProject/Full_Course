package com.fullcourse.admin.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullcourse.admin.AdminVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminNoticeController {

	@Autowired
	private AdminNoticeService service;

	/*
	 * @GetMapping("/admin/notice") public String notice() { return
	 * "thymeleaf/admin/notice/selectAll"; }
	 */

	@GetMapping("/admin/notice/selectAll")
	public String selectAll(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model,HttpSession session) {
		log.info("/selectAll...");
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
		
		AdminVO admin = (AdminVO) session.getAttribute("admin");
		if (admin != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + admin.getAdminId());
			model.addAttribute("loginId", admin.getAdminId());
			model.addAttribute("loggedIn", loggedIn);

		} else {

			log.info("로그인한사람이 없습니다");
			 return "redirect:/admin/login";
		}

		List<NoticeVO> vos = service.selectAllPageBlock(cpage, pageBlock);

		model.addAttribute("vos", vos);

		// member테이블에 들어있는 모든회원수는 몇명?
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
		model.addAttribute("sidebar","thymeleaf/admin/sidebar");
		model.addAttribute("content", "thymeleaf/admin/notice/th_selectAll");
		model.addAttribute("title", "관리자 목록");
		return "thymeleaf/admin/th_adminLayout_main";
	}

	@GetMapping("admin/notice/insert")
	public String insert(Model model) {
		model.addAttribute("title", "공지사항 글쓰기");

		return "thymeleaf/admin/notice/th_insert";
	}

	@PostMapping("/admin/notice/insertOK")
	public String insertOK(NoticeVO vo) {
		log.info("insertOK");
		log.info("vo:{}", vo);
		int result = service.insertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:/admin/notice/selectAll";
		} else {
			return "thymeleaf/admin/notice/th_selectAll";
		}
	}

	@GetMapping("admin/notice/selectOne")
	public String selectOne(NoticeVO vo, Model model) {
		log.info("/selectOne...");
		log.info("vo:{}", vo);

		NoticeVO vo2 = service.selectOne(vo);

		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/admin/notice/th_selectOne");
		model.addAttribute("title", "공지사항 글 ");
		return "thymeleaf/admin/notice/th_selectOne";
	}

	@GetMapping("admin/notice/update")
	public String showUpdateForm(@RequestParam("noticeNum") int noticeNum, Model model) {
		log.info("num", noticeNum);
		NoticeVO notice = service.update(noticeNum);
		model.addAttribute("notice", notice);
		return "thymeleaf/admin/notice/th_update";
	}

	@PostMapping("admin/notice/updateOK")
	public String updateOK(NoticeVO vo) {
		log.info("/updateOK...");
		log.info("vo:{}", vo);

		int result = service.updateOK(vo);
		log.info("result:{}", result);


		return "redirect:selectOne?noticeNum=" + vo.getNoticeNum();
		// return "redirect:selectAll";
	}

	@GetMapping("admin/notice/delete")
	public String delete(Model model) {
		log.info("/delete...");

		model.addAttribute("content", "thymeleaf/member/th_delete");
		model.addAttribute("title", "회원삭제");
		return "thymeleaf/member/th_layout_main";
	}

//	// deleteOK 만들기
//	@PostMapping("admin/notice/deleteOK")
//	public String deleteOK(NoticeVO vo) {
//		log.info("/deleteOK...");
//		log.info("vo:{}", vo);
//
//		int result = service.deleteOK(vo);
//		log.info("result:{}", result);
//
//		return "redirect:/admin/notice/selectAll";
//	}
	
	@PostMapping("admin/notice/deleteOK")
	public String deleteOK(NoticeVO vo, RedirectAttributes redirectAttributes) {
	    int result = service.deleteOK(vo);
	    if (result == 1) {
	        redirectAttributes.addAttribute("success", true);
	    } else {
	        redirectAttributes.addAttribute("success", false);
	    }
	    return "redirect:/admin/notice/selectAll";
	}


}
