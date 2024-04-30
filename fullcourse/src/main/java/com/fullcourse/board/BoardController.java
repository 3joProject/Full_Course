package com.fullcourse.board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullcourse.member.MemberVO;
import com.fullcourse.WebConfig;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Value("${file.dir}")
	String realPath;
	
	@Autowired
	private BoardService boardService;
	
	// 로그인 상태 확인 메소드
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("member") != null;
    }
    
    
    //게시글 작성
    @GetMapping("/insert")
    public String insert(Model model, HttpSession session) {
    	
    	MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {

            boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);

        } else {

            log.info("로그인한사람이 없습니다");

        }
    	
    	if (!isLoggedIn(session)) {
            return "redirect:/login";  // 로그인 페이지 경로를 확인하고 적절히 수정하세요.
        }
	    	
		model.addAttribute("content", "thymeleaf/board/insert");
		model.addAttribute("title", "게시글 작성");
		
		return "thymeleaf/board/layout_main";
	}
    
    @PostMapping("/insertOK")
	public String insertOK(BoardVO boardVO, HttpSession session) throws IllegalStateException, IOException {
		
		MemberVO loggedInMember = (MemberVO) session.getAttribute("member");
	    if (loggedInMember == null) {
	        return "redirect:/login"; // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트
	    }
	    
		boardVO.setBoardWriter(loggedInMember.getMemberId());
		
		log.info(realPath);

		String originName = boardVO.getFile().getOriginalFilename();

		log.info("getOriginalFilename:{}", originName);

		if (originName.length() == 0) {
			boardVO.setBoardImage("default.png");// 이미지선택없이 처리할때
		} else {
			String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));

			boardVO.setBoardImage(save_name);

			File uploadFile = new File(realPath, save_name);
			boardVO.getFile().transferTo(uploadFile);// 원본 이미지저장

			BufferedImage original_buffer_img = ImageIO.read(uploadFile);
			BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = thumb_buffer_img.createGraphics();
			graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

			File thumb_file = new File(realPath, "thumb_" + save_name);

			ImageIO.write(thumb_buffer_img, save_name.substring(save_name.lastIndexOf(".") + 1), thumb_file);

		}
		
		int result = boardService.insertOK(boardVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
    
    
	@GetMapping("/selectAll")
	public String selectAllBoards(@RequestParam(defaultValue = "1") int cpage, @RequestParam(defaultValue = "9") int pageBlock, Model model, HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {

            boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);

        } else {

            log.info("로그인한사람이 없습니다");

        }
        
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
	public String selectOne(@RequestParam("boardNum") int boardNum, Model model, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {

            boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);

        } else {

            log.info("로그인한사람이 없습니다");

        }
        
		log.info("Select board with boardNum: {}", boardNum);
	    BoardVO board = boardService.getBoardById(boardNum);
	    if (board != null) {
	        model.addAttribute("vo", board);
	        model.addAttribute("content", "thymeleaf/board/selectOne");
			model.addAttribute("title", "게시판 상세");
			
	        return "thymeleaf/board/layout_main";
	    } else {
	        return "redirect:/board/selectAll";
	    }
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("boardNum") int boardNum, Model model, HttpSession session) {
	    if (!isLoggedIn(session)) {
	        return "redirect:/login";
	    }

	    BoardVO board = boardService.getBoardById(boardNum);
	    model.addAttribute("content", "thymeleaf/board/update");
		model.addAttribute("title", "게시글 수정");
		
	    if (board != null) {
	        model.addAttribute("vo", board);
	        return "thymeleaf/board/layout_main"; // 해당 뷰 파일 이름 확인
	    } else {
	        return "redirect:/board/selectAll";
	    }
	    
	    
	}
	
	@PostMapping("/updateOK")
	public String updateOK(BoardVO vo, RedirectAttributes redirectAttributes, HttpSession session) {
	    log.info("/updateOK...");
	    log.info("vo:{}", vo);

	    // Check and set the writer if null
	    if (vo.getBoardWriter() == null) {
	        MemberVO loggedInMember = (MemberVO) session.getAttribute("member");
	        if (loggedInMember != null) {
	            vo.setBoardWriter(loggedInMember.getMemberId());
	        } else {
	            return "redirect:/login"; // Redirect to login if no user session
	        }
	    }
	   
	    
	    int result = boardService.updateOK(vo);
	    log.info("result:{}", result);

	    redirectAttributes.addAttribute("boardNum", vo.getBoardNum());
	    return "redirect:/board/selectOne?boardNum=" + vo.getBoardNum();
	}
	
	@GetMapping("/delete")
	public String delete(Model model) {
		log.info("/delete...");

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