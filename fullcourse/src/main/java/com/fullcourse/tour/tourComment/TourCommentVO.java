package com.fullcourse.tour.tourComment;

import org.springframework.web.multipart.MultipartFile;

import com.fullcourse.tour.TourVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourCommentVO {

	private int tourcoNum;
    private String tourcoContent;
    private String tourcoWriter;
    private String tourcoDate; // LocalDate로 변경하는 것이 좋을 수 있습니다.
    private int tourcoTnum;
    private TourCoStatus tourcoStatus;
	
}
