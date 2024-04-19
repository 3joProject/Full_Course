package com.fullcourse.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.board.BoardVO;

@Mapper
public interface BoardMapper {
	

	public int deleteOK(BoardVO boardVO);

	public int insertOK(BoardVO boardVO);

	public int updateOK(BoardVO boardVO);
	
	public BoardVO selectBoardById(int boardNum);
	
	public List<BoardVO> selectAllBoardsPageBlock(Map<String, Integer> map);
	
	public BoardVO selectOne(BoardVO boardVO);
	
	public List<BoardVO> searchListWIRTER_PAGE(Map<String, Object> map);
	
	public List<BoardVO> searchListTITLE_PAGE(Map<String, Object> map);
	
	public int getTotalRows();
	
	public int search_total_rows_writer(Map<String, String> map);

	public int search_total_rows_title(Map<String, String> map);

}
