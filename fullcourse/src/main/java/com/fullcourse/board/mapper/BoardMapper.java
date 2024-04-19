package com.fullcourse.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.board.BoardVO;

@Mapper
public interface BoardMapper {
	
	public List<BoardVO> selectAllBoards();

	public int deleteOK(BoardVO boardVO);

	public int insertOK(BoardVO boardVO);

	public int updateOK(BoardVO boardVO);
	
	public BoardVO selectBoardById(int boardNum);
	
	public List<BoardVO> selectAllBoardsPageBlock(Map<String, Integer> map);
	
	public int getTotalRows();

}
