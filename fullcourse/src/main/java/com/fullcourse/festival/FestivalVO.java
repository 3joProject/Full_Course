package com.fullcourse.festival;

import com.fullcourse.paging.PagingVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FestivalVO extends PagingVO {
	
	private int festivalNum;

    private String festivalName;
    private String festivalAddress;
    private String festivalContent;
    private String festivalStart;
    private String festivalFinal;
    private String festivalTel;
    private int festivalViews;
    private int festivalThumbUp;
    private String festivalImg;

    
}
