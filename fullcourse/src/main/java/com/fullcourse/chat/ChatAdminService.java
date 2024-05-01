package com.fullcourse.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullcourse.chat.mapper.ChatAdminMapper;
import com.fullcourse.chat.mapper.ChatMapper;

@Service
public class ChatAdminService {
	
	@Autowired
    private ChatAdminMapper adminMapper;
	
		public ChatAdminVO login(String adminId, String adminPw) {
			return adminMapper.adminLogin(adminId, adminPw);
		}

	    public List<ChatRoomVO> getAllChatRooms() {
	        return adminMapper.getAllChatRooms();
	    }
	    
	    public ChatRoomVO getChatRoom(int chatRoomId) {
		    return adminMapper.getChatRoomById(chatRoomId);  // 해당 채팅방 정보를 가져옴
		}
	    
	    public void deleteChatRoom(int chatRoomId) {
	        adminMapper.deleteChatRoom(chatRoomId);
	    }
	    
}
