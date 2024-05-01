package com.fullcourse.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fullcourse.chat.ChatAdminVO;
import com.fullcourse.chat.ChatRoomVO;

@Mapper
public interface ChatAdminMapper {
	
	ChatAdminVO adminLogin(@Param("adminId") String adminId, @Param("password") String password);
	
    List<ChatRoomVO> getAllChatRooms();
    
    ChatRoomVO getChatRoomById(int chatRoomId);

    @Delete("DELETE FROM chatRoom WHERE chatRoomId = #{chatRoomId}")
    void deleteChatRoom(int chatRoomId);  
}
