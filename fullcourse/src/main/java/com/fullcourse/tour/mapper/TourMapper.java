package com.fullcourse.tour.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.TourVO;

@Mapper
public interface TourMapper {


	public int TourInsertOK(TourVO vo);

	public TourVO TourSelectOne(TourVO vo);

	public int getTotalRows();

	public List<MemberVO> TourSelectAll(Map<String, Integer> map);

}
