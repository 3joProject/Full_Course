package com.fullcourse.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 로그인 상태 확인 메소드
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("member") != null;
    }
    
    
    //게시글 작성
    @GetMapping("/insert")
    public String insert(Model model, HttpSession session) {
    	if (!isLoggedIn(session)) {
            return "redirect:/login";  // 로그인 페이지 경로를 확인하고 적절히 수정하세요.
        }
	    	
		model.addAttribute("content", "thymeleaf/board/insert");
		model.addAttribute("title", "게시글 작성");
		
		return "thymeleaf/board/layout_main";
	}
    
    @PostMapping("/insertOK")
	public String insertOK(BoardVO boardVO, HttpSession session) {
		
		MemberVO loggedInMember = (MemberVO) session.getAttribute("member");
	    if (loggedInMember == null) {
	        return "redirect:/login"; // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트
	    }
	    
		boardVO.setBoardWriter(loggedInMember.getMemberId());
		
		int result = boardService.insertOK(boardVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
    
    
	@GetMapping("/selectAll")
	public String selectAllBoards(@RequestParam(defaultValue = "1") int cpage, @RequestParam(defaultValue = "9") int pageBlock, Model model) {
		
		log.info("/selectAll");
		
		List<BoardVO> boards = boardService.selectAllBoardsPageBlock(cpage, pageBlock);
		model.addAttribute("boards", boards);
		
		int total_rows = boardService.getTotalRows();
		log.info("total_rows:" + total_rows);

		
		//board 테이블에 있는 게시글 수
		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		
		//페이지 링크 개수
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);
		
		model.addAttribute("content", "thymeleaf/board/selectAll");
		model.addAttribute("title", "게시판목록");
		
		return "thymeleaf/board/layout_main";
	}
	
	@GetMapping("/selectOne")
	public String m_selectOne(BoardVO boardVO, Model model) {
		log.info("boardVO:{}", boardVO);

		BoardVO vo = boardService.selectOne(boardVO);

		model.addAttribute("vo", vo);

		model.addAttribute("content", "thymeleaf/board/selectOne");
		model.addAttribute("title", "게시판 상세");
		
		return "thymeleaf/board/layout_main";
	}
	
	// m_updateOK 만들기
	@PostMapping("/updateOK")
	public String updateOK(BoardVO boardVO) {

		int result = boardService.updateOK(boardVO);

		return "redirect:selectOne?boardNum=" + boardVO.getBoardNum();

	}
		
	@GetMapping("/delete")
	public String m_delete(Model model) {
		log.info("/m_delete...");

		model.addAttribute("content", "thymeleaf/board/delete");
		model.addAttribute("title", "글삭제");
		return "thymeleaf/board/layout_main";
	}

	@PostMapping("/deleteOK")
	public String deleteOK(BoardVO boardVO) {

		int result = boardService.deleteOK(boardVO);

		return "redirect:selectAll";
	}
}