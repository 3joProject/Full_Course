package com.fullcourse.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ChatAdminController {

    @Autowired
    private ChatAdminService chatAdminService;

    @GetMapping("/chat/admindashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("chatRooms", chatAdminService.getAllChatRooms());
        return "thymeleaf/chat/adminDashboard";
    }

    @GetMapping("/chat/chatRoom/{chatRoomId}")
    public String adminChatRoom(@PathVariable int chatRoomId, Model model) {
        model.addAttribute("chatRoom", chatAdminService.getChatRoom(chatRoomId));
        return "thymeleaf/chat/adminChatRoom";
    }
    
    
}
