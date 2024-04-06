package com.fullcourse.tour;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourVO {

	private int tour_num;
	private String tour_name;
	private String tour_address;
	private String tour_content;
	private String tour_usetime;
	private String tour_tel;
	private int tour_view;
	private int tour_thumbUp;
	private String tour_img;
	private MultipartFile file;

}
