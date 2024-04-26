package com.fullcourse.admin.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVO {
	
	private int memberNum;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAddress;
	private String memberTel;
	private String memberEmail;
	private LocalDateTime memberDate;
	private String memberImg;
	private String memberSns;

}
