package com.fullcourse.member;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
		private int memberNum;
	    private String memberId;
	    private String memberPw;
	    private String memberName;
	    private String memberAddress;
	    private String memberTel;
	    private String memberEmail;
	    private Date memberDate;
	    private String memberImg;
	    private String memberSns;
	    private MultipartFile file;
	   
	}