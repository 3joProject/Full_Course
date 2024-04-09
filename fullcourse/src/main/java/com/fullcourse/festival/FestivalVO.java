package com.fullcourse.festival;

import org.springframework.web.multipart.MultipartFile;

import com.fullcourse.tour.TourVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FestivalVO {
	
	private int festivalNum;

    private String festivalName;
    private String festivalAddress;
    private String festivalContent;
    private String festivalStart;
    private String festivalFinal;
    private String festivalUsetime;
    private String festivalTel;
    private int festivalViews;
    private int festivalThumbUp;
    private String festivalImg;

    
}
