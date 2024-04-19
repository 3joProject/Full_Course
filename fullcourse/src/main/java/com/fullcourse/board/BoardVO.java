package com.fullcourse.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	
	private int boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Date boardDate;
	private String boardImage;
	
	
}
