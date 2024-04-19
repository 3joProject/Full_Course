package com.fullcourse.tour;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourVO {

	private int tourNum;
	private String tourName;
	private String tourAddress;
	private String tourContent;
	private String tourTel;
	private int tourViews;
	private int tourThumbUp;
	private String tourImg;
	private double longtitue; // 경도 mapx
	private double lattitue; // 위도 mapy
	private MultipartFile file;

	}
