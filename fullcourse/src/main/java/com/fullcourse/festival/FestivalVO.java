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
	
	private int festival_num;
    private String festival_name;
    private String festival_address;
    private String festival_content;
    private String festival_start;
    private String festival_final;
    private String festival_usetime;
    private String festival_tel;
    private int festival_view;
    private int festival_thumbUp;
    private String festival_img;
    private MultipartFile file;
}
