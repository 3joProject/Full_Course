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

	private int tourco_num;
    private String tourco_content;
    private String tourco_writer;
    private String tourco_date; // LocalDate로 변경하는 것이 좋을 수 있습니다.
    private int tourco_tnum;
    private TourCoStatus tourco_status;
}
