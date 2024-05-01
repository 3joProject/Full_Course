package com.fullcourse.chat;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fullcourse.board.BoardController;
import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@GetMapping("/chat/chatStart")
	public String getChatStartPage(Model model, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {

            boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);

        } else {

            log.info("로그인한사람이 없습니다");

        }
        
		return "thymeleaf/chat/chatStart"; //채팅 시작 페이지 반환
	}
	
	@PostMapping("/chat/new")
    public String createNewChatRoom(Model model) {
        ChatRoomVO chatRoom = new ChatRoomVO();
        chatService.createChatRoom(chatRoom);
        return "redirect:/chat/" + chatRoom.getChatRoomId();
    }

	@GetMapping("/chat/{chatRoomId:\\d+}")
	public String getChatRoom(@PathVariable int chatRoomId, Model model) {
	    model.addAttribute("chatRoomId", chatRoomId);
	    return "thymeleaf/chat/chatRoom";
	}

    @MessageMapping("/chat/{chatRoomId}")
    public void sendMessage(@DestinationVariable int chatRoomId, @Payload ChatMessageVO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/" + chatRoomId, chatMessage);
    }
	
    @PostMapping("/chat/leave/{chatRoomId}")
    public String leaveChatRoom(@PathVariable int chatRoomId) {
        chatService.deleteChatRoom(chatRoomId);  // 채팅방 삭제 로직 호출
        return "redirect:/chat/chatStart";  // 채팅 시작 페이지로 리디렉션
    }
}	
