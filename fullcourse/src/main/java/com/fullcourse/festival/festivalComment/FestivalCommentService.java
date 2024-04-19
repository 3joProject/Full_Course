package com.fullcourse.festival.festivalComment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.festival.festivalComment.mapper.FestivalCommentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FestivalCommentService {

	@Autowired
	private FestivalCommentMapper mapper;

	
	public int festivalCommentInsertOK(FestivalCommentVO vo) {
		return mapper.festivalCommentInsertOK(vo);
	}


	public int festivalCommentDeleteOK(FestivalCommentVO vo) {
		return mapper.festivalCommentDeleteOK(vo);
	}

	public int festivalCommentUpdateOK(FestivalCommentVO vo) {
		return mapper.festivalCommentUpdateOK(vo);
	}

	
	public List<FestivalCommentVO> festivalCommentSelectAll(FestivalCommentVO vo) {
		log.info("festivalCommentSelectAll()....");
		log.info(vo.toString());

		return mapper.festivalCommentSelectAll(vo);
	}


	//신고
//	public int festivalCommentReport(FestivalCommentVO vo) {
//		// TODO Auto-generated method stub
//		return mapper.festivalCommentReport(vo);
//	}


}
