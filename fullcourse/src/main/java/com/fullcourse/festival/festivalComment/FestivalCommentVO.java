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
	private int festivalco_num;
    private String festivalco_content;
    private String festivalco_writer;
    private String festivalco_date; // LocalDate로 변경하는 것이 좋을 수 있습니다.
    private int festivalco_fnum;
    private FestivalCoStatus festivalco_status;

}
