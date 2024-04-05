package com.example.fullcourse.admin.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVO {
	
	private int member_num;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_address;
	private String member_tel;
	private String member_email;
	private LocalDateTime member_date;
	private String member_img;
	private String member_sns;

}
