package com.fullcourse.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class ChatAdminController {

    @Autowired
    private ChatAdminService chatAdminService;
    
    // 로그인 페이지로 이동
    @GetMapping("/chat/adminChatlogin")
    public String showLogin() {
        return "thymeleaf/chat/adminChatlogin";  // 로그인 페이지의 올바른 경로
    }
    
    // 로그인 처리
    @PostMapping("/chat/adminChatlogin")
    public String login(@RequestParam("adminId") String adminId, // adminName을 adminId로 변경
            @RequestParam String adminPw, 
            HttpSession session) {
        // 더미 데이터를 이용한 간단한 로그인 체크
        if ("admin1".equals(adminId) && "password1".equals(adminPw)) {
            session.setAttribute("admin", adminId); // 세션에 admin 속성 추가
            return "redirect:/chat/adminDashboard";
        } else {
            return "redirect:/chat/adminChatlogin?error=ture"; // 실패 시, 로그인 실패 페이지로 리다이렉트
        }
    }

    //로그아웃 처리
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/chat/adminChatlogin"; // 로그인 페이지로 리다이렉트
    }
    
    
    @GetMapping("/chat/adminDashboard")
    public String adminDashboard(HttpSession session, Model model) {
    	if (session.getAttribute("admin") != null) {
            model.addAttribute("chatRooms", chatAdminService.getAllChatRooms());
            return "thymeleaf/chat/adminDashboard";
        }
        return "redirect:/chat/adminChatlogin";  // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
    }

    @GetMapping("/chat/chatRoom/{chatRoomId}")
    public String adminChatRoom(@PathVariable int chatRoomId, Model model) {
        model.addAttribute("chatRoom", chatAdminService.getChatRoom(chatRoomId));
        return "thymeleaf/chat/adminChatRoom";
    }
    
    
}
