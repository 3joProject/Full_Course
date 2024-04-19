package com.fullcourse.tour.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.TourVO;

@Mapper
public interface TourMapper {


	public int tourInsertOK(TourVO vo);

	public TourVO tourSelectOne(TourVO vo);

	public int getTotalRows();

	public List<TourVO> tourSelectAll(Map<String, Integer> map);

	public int tourDeleteOK(TourVO vo);

	public int tourUpdateOK(TourVO vo);

	public List<TourVO> searchListADDRESS_PAGE(Map<String, Object> map);

	public List<TourVO> searchListNAME_PAGE(Map<String, Object> map);

	public int search_total_rows_address(Map<String, String> map);

	public int search_total_rows_tourName(Map<String, String> map);

	public List<TourVO> tourSelectAllTop();

	public int updateLikeCount(TourVO vo);

	public int updateViewCount(TourVO vo);

	public List<TourVO> searchWordaddress(Map<String, String> map);

	public List<TourVO> searchListAddressPageBlock(Map<String, Object> map);



	

}
