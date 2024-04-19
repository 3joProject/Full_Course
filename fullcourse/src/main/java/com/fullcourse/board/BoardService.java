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
	
	public BoardVO selectOne(BoardVO boardVO) {
		return boardMapper.selectOne(boardVO);
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
	
	public List<BoardVO> searchListPageBlock(
			String searchKey, String searchWord, 
			int cpage, int pageBlock) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		if(searchKey.equals("id")) {
			return boardMapper.searchListWIRTER_PAGE(map);
		}else {
			return boardMapper.searchListTITLE_PAGE(map);
		}
	}

	public int getTotalRows() {
        return boardMapper.getTotalRows();
    }
	
	public int getSearchTotalRows(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("id")) {
			return boardMapper.search_total_rows_writer(map);
		}else {
			return boardMapper.search_total_rows_title(map);
		}
	}
	
}