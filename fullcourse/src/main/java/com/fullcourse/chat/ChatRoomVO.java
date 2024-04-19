package com.fullcourse.chat;

import java.time.LocalDateTime;

public class ChatRoomVO {
	
	private int chatRoomId;
    private LocalDateTime chatCreatedAt;
    
	public int getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public LocalDateTime getChatCreatedAt() {
		return chatCreatedAt;
	}
	public void setChatCreatedAt(LocalDateTime chatCreatedAt) {
		this.chatCreatedAt = chatCreatedAt;
	}
	
	
	

}
