package com.fullcourse.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullcourse.chat.mapper.ChatMapper;

@Service
public class ChatService {
	
	 	@Autowired
	    private ChatMapper chatMapper;

	    public List<ChatRoomVO> getAllChatRooms() {
	        return chatMapper.getAllChatRooms();
	    }

	    public void createChatRoom(ChatRoomVO chatRoom) {
	        chatMapper.createChatRoom(chatRoom);
	    }
	    
	    @Transactional  // 트랜잭션 관리를 위한 어노테이션
	    public void deleteChatRoom(int chatRoomId) {
	        chatMapper.deleteChatRoom(chatRoomId);
	    }
}
