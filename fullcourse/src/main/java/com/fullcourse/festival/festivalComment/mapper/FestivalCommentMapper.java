package com.fullcourse.festival.festivalComment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.festival.festivalComment.FestivalCommentVO;


@Mapper
public interface FestivalCommentMapper {

	public int festivalCommentInsertOK(FestivalCommentVO vo);

	public int festivalCommentDeleteOK(FestivalCommentVO vo);

	public int festivalCommentUpdateOK(FestivalCommentVO vo);

	public List<FestivalCommentVO> festivalCommentSelectAll(FestivalCommentVO vo);

	//신고
//	public int festivalCommentReport(FestivalCommentVO vo);

}
