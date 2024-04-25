package com.fullcourse.festival.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.festival.FestivalVO;
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

	public int updateViewCount(FestivalVO vo);
	
	public int updateLikeCount(FestivalVO vo);

	public List<FestivalVO> searchListAddressPageBlock(Map<String, Object> map);

	 // 리스트 토탈 갯수 (검색 포함)
    int selectListTotalCount(FestivalVO festivalVO);

    // 리스트 (검색포함)
    List<FestivalVO> selectFestivalListWithPaging(FestivalVO festivalVO);
    
    //best Top3
    public List<FestivalVO> festivalSelectAllTop(FestivalVO vo);

    //좋아요 확인
	public int getFestivalLikeCount(int memberNum, int festivalNum);

	public List<FestivalVO> selectFestivalViewTopListWithPaging(FestivalVO festivalVO);



}
