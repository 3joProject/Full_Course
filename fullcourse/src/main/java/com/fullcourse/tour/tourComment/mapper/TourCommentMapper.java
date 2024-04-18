package com.fullcourse.tour.tourComment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.tour.tourComment.TourCommentVO;

@Mapper
public interface TourCommentMapper {

	public int tourCommentInsertOK(TourCommentVO vo);

	public int tourCommentDeleteOK(TourCommentVO vo);

	public int tourCommentUpdateOK(TourCommentVO vo);

	public List<TourCommentVO> tourCommentSelectAll(TourCommentVO vo);

	//신고
//	public int tourCommentReport(TourCommentVO vo);

}
