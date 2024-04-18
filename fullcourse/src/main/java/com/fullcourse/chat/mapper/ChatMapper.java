package com.fullcourse.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.chat.ChatRoomVO;

@Mapper
public interface ChatMapper {
    List<ChatRoomVO> getAllChatRooms();
    ChatRoomVO getChatRoomById(int chatRoomId);
    void createChatRoom(ChatRoomVO chatRoom);
    void deleteChatRoom(int chatRoomId);
}
