package com.fullcourse.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.chat.ChatRoomVO;

@Mapper
public interface ChatAdminMapper {
    List<ChatRoomVO> getAllChatRooms();
    ChatRoomVO getChatRoomById(int chatRoomId);
}
