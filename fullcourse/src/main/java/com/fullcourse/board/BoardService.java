package com.fullcourse.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.board.mapper.BoardMapper;


@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public int insertOK(BoardVO boardVO) {
		return boardMapper.insertOK(boardVO);
	}

	public int updateOK(BoardVO boardVO) {
		return boardMapper.updateOK(boardVO);
	}

	public int deleteOK(BoardVO boardVO) {
		return boardMapper.deleteOK(boardVO);
	}
	
	public List<BoardVO> selectAllBoards() {
		return boardMapper.selectAllBoards();
	}

	public BoardVO getBoardById(int boardNum) {
		return boardMapper.selectBoardById(boardNum);
	}
	
	public List<BoardVO> selectAllBoardsPageBlock(int cpage, int pageBlock) {
		
		int startRow = (cpage-1) * pageBlock + 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return boardMapper.selectAllBoardsPageBlock(map);
	}
	
	public int getTotalRows() {
        return boardMapper.getTotalRows();
    }
	
	
	
}