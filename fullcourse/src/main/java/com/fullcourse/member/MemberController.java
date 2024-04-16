package com.fullcourse.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/memberinsert")
	public String insert(Model model) {
		
		log.info("/memberinsert");
		
		model.addAttribute("title", "회원가입");
		
		return "thymeleaf/member/insert";
	}
	
	@PostMapping("/memberinsertOK")
	public String insertOK(MemberVO memberVO) {
		
		log.info("/memberinsertOK...");

		int result = memberService.insertOK(memberVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	// 로그인 폼 페이지
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "로그인");
        return "thymeleaf/member/login";
    }

    @PostMapping("/login")
    public String loginProcess(String memberId, String memberPw, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("로그인 시도: {}", memberId);
        MemberVO member = memberService.login(memberId, memberPw);

        if (member != null) {
            session.setAttribute("member", member);
            log.info("로그인 성공: {}", memberId);
            return "redirect:/mypage";
        } else {
            log.error("로그인 실패: {}", memberId);
            redirectAttributes.addFlashAttribute("message", "Invalid ID or Password");
            return "redirect:/login";
        }
    }
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/login";  // 로그인이 되어있지 않다면 로그인 페이지로 리다이렉트
        }

        model.addAttribute("member", member);
        return "thymeleaf/member/mypage";  // 마이페이지 뷰로 이동
    }
	
	
}