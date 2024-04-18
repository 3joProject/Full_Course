package com.fullcourse.festival.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.festival.FestivalVO;

@Mapper
public interface FestivalMapper {
	
	public int festivalInsertOK(FestivalVO vo);

	public FestivalVO festivalSelectOne(FestivalVO vo);

	public int getTotalRows();

	public List<FestivalVO> festivalSelectAll(Map<String, Integer> map);

	public int festivalDeleteOK(FestivalVO vo);

	public int festivalUpdateOK(FestivalVO vo);

	public List<FestivalVO> searchListADDRESS_PAGE(Map<String, Object> map);

	public List<FestivalVO> searchListNAME_PAGE(Map<String, Object> map);

	public int search_total_rows_address(Map<String, String> map);

	public int search_total_rows_festivalName(Map<String, String> map);

	public List<FestivalVO> festivalSelectAllTop();

}
