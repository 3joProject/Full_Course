package com.fullcourse.festival.festivalComment;

import org.springframework.web.multipart.MultipartFile;

import com.fullcourse.tour.TourVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FestivalCommentVO {
	private int festivalcoNum;
    private String festivalcoContent;
    private String festivalcoWriter;
    private String festivalcoDate; // LocalDate로 변경하는 것이 좋을 수 있습니다.
    private int festivalcoFnum;
    private FestivalCoStatus festivalcoStatus;

}
