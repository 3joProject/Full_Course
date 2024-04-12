package com.fullcourse.tour.tourComment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.tour.TourVO;
import com.fullcourse.tour.tourComment.mapper.TourCommentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TourCommentService {

	@Autowired
	private TourCommentMapper mapper;

	
	public int tourCommentInsertOK(TourCommentVO vo) {
		return mapper.tourCommentInsertOK(vo);
	}


	public int tourCommentDeleteOK(TourCommentVO vo) {
		return mapper.tourCommentDeleteOK(vo);
	}

	public int tourCommentUpdateOK(TourCommentVO vo) {
		return mapper.tourCommentUpdateOK(vo);
	}

	
	public List<TourCommentVO> tourCommentSelectAll(TourCommentVO vo) {
		log.info("tourCommentSelectAll()....");
		log.info(vo.toString());

		return mapper.tourCommentSelectAll(vo);
	}

	
}
