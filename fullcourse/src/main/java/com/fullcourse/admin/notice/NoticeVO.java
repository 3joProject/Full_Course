package com.fullcourse.admin.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeVO {
	
	private int noticeNum;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private String noticeDate;
	
}
