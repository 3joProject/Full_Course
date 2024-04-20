package com.fullcourse.admin.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.admin.AdminVO;
import com.fullcourse.admin.notice.NoticeVO;

@Mapper
public interface AdminNoticeMapper {

	public List<NoticeVO> selectAllPageBlock(Map<String, Integer> map);

	int getTotalRows();

	public int insertOK(NoticeVO vo);

	public NoticeVO selectOne(NoticeVO vo);

	public int updateOK(NoticeVO vo);

	public int deleteOK(NoticeVO vo);

	public NoticeVO update(int noticeNum);



}
