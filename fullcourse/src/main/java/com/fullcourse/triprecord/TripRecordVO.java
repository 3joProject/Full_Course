package com.fullcourse.triprecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TripRecordVO {

	
	private int triprecNum;
	private int triprecMnum;
	private String triprecTripdate;
	private String triprecContent;
	private String triprecImage;
	private String triprecDate;
	private String triprecAddress;
	
}
