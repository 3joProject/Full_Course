package com.fullcourse.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullcourse.chat.mapper.ChatMapper;

@Service
public class ChatAdminService {
	
	 	@Autowired
	    private ChatMapper chatMapper;

	    public List<ChatRoomVO> getAllChatRooms() {
	        return chatMapper.getAllChatRooms();
	    }
	    
	    public ChatRoomVO getChatRoom(int chatRoomId) {
		    return chatMapper.getChatRoomById(chatRoomId);  // 해당 채팅방 정보를 가져옴
		}
	    
	    
}
