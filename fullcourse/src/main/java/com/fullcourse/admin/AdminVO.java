package com.fullcourse.admin;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminVO {
	
	private String adminNum;
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminEmail;
	private String adminDate;

}
