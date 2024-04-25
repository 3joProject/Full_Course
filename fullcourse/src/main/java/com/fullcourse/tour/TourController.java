package com.fullcourse.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.member.MemberVO;
import com.fullcourse.paging.PaginationInfo;
import com.fullcourse.tour.tourComment.TourCommentService;
import com.fullcourse.tour.tourComment.TourCommentVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TourController {

	@Autowired
	private TourService service;
	@Autowired
	private TourCommentService comService;

	// 여행 페이지 메인
//	@GetMapping("/tour")
//	public String tourMain(@RequestParam(defaultValue = "1") int cpage, @RequestParam(defaultValue = "9") int pageBlock,
//			Model model) {
//		log.info("/tourMain...");
//
//		List<TourVO> vos = service.tourSelectAll(cpage, pageBlock);
//
//		// best 여행지
//		List<TourVO> vos2 = service.tourSelectAllTop();
//		model.addAttribute("vos2", vos2);
//		model.addAttribute("vos", vos);
//
//		// tour테이블에 들어있는 모든여행지수는 몇개?
//		int total_rows = service.getTotalRows();
//		log.info("total_rows:" + total_rows);
//
//		int totalPageCount = 1;
//		if (total_rows / pageBlock == 0) {
//			totalPageCount = 1;
//		} else if (total_rows % pageBlock == 0) {
//			totalPageCount = total_rows / pageBlock;
//		} else {
//			totalPageCount = total_rows / pageBlock + 1;
//		}
//		// 페이지 링크 몇개?
//		log.info("totalPageCount:" + totalPageCount);
//		model.addAttribute("totalPageCount", totalPageCount);
//
//		model.addAttribute("content", "thymeleaf/tour/th_tourMain");
//		model.addAttribute("title", "여행지");
//		return "thymeleaf/tour/th_tourLayout_main";
//	}

	// 상세정보로 이동 새로운버젼
	@GetMapping("/tour/tourDetails")
	public String tourDetails(TourVO vo, Model model, HttpSession session) {
		log.info("tourDetails...");
		log.info("vo:{}", vo);
		
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    if (member != null) {
	    	session.setAttribute("tourLikeMemberNum ", member.getMemberNum());
            session.setAttribute("tourLikeTourNum ", vo.getTourNum());
            log.info("tourLikeMemberNum: {}", member.getMemberNum());
            log.info("tourLikeTourNum: {}", vo.getTourNum());

            session.setMaxInactiveInterval(3600);
	        // 로그인된 사용자인 경우, 좋아요 상태를 확인하고 업데이트합니다.
	        int likeCount = service.getTourLikeCount(member.getMemberNum(), vo.getTourNum()); // 회원번호와 글번호를 통해 좋아요 상태 확인
	        log.info("좋아요 체크성공");
	        log.info("likeCount: {}", likeCount);
	        model.addAttribute("commentWriter", member.getMemberId());
	        model.addAttribute("likeCount", likeCount); // 좋아요 상태를 View로 전달
	    }
	    log.info("Member Number: {}", member);
		service.updateviewCount(vo);
		log.info("updateview..");
		log.info("vo:{}", vo);
		TourVO vo2 = service.tourSelectOne(vo);
		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/tour/th_tourDetails");
		model.addAttribute("title", "여행상세페이지");

		// 댓글목록 처리로직
		TourCommentVO cvo = new TourCommentVO();
		cvo.setTourcoTnum(vo.getTourNum());
		List<TourCommentVO> cvos = comService.tourCommentSelectAll(cvo);

		model.addAttribute("cvos", cvos);

		return "thymeleaf/tour/th_tourLayout_main";
	}
//	@GetMapping("/tour/tourDetails")
//	public String tourDetails(TourVO vo, Model model, HttpSession session) {
//	    log.info("tourDetails...");
//	    log.info("vo:{}", vo);
//	    
//	    // 세션에서 로그인 여부를 확인합니다.
//	    boolean isLoggedIn = session.getAttribute("member") != null;
//	    if (isLoggedIn) {
//	        // 로그인한 경우의 처리
//	        MemberVO member = (MemberVO) session.getAttribute("member");
//	        session.setAttribute("tourLikeMemberNum ", member.getMemberNum());
//	        session.setAttribute("tourLikeTourNum ", vo.getTourNum());
//	        log.info("tourLikeMemberNum: {}", member.getMemberNum());
//	        log.info("tourLikeTourNum: {}", vo.getTourNum());
//
//	        session.setMaxInactiveInterval(3600);
//	        
//	        int likeCount = service.getTourLikeCount(member.getMemberNum(), vo.getTourNum());
//	        log.info("좋아요 체크성공");
//	        log.info("likeCount: {}", likeCount);
//
//	        model.addAttribute("likeCount", likeCount);
//	    } else {
//	        // 비로그인인 경우의 처리
//	        log.info("로그인되지 않았습니다.");
//	    }
//
//	    
//	    service.updateviewCount(vo);
//	    log.info("updateview..");
//	    log.info("vo:{}", vo);
//	    TourVO vo2 = service.tourSelectOne(vo);
//	    model.addAttribute("vo2", vo2);
//
//	    model.addAttribute("content", "thymeleaf/tour/th_tourDetails");
//	    model.addAttribute("title", "여행상세페이지");
//
//	    TourCommentVO cvo = new TourCommentVO();
//	    cvo.setTourcoTnum(vo.getTourNum());
//	    List<TourCommentVO> cvos = comService.tourCommentSelectAll(cvo);
//	    model.addAttribute("cvos", cvos);
//
//	    return "thymeleaf/tour/th_tourLayout_main";
//	}

	// 여행지 입력페이지로 이동
	@GetMapping("/tour/tourInsert")
	public String tourInsert(Model model) {
		log.info("tourInsert...");

		model.addAttribute("content", "thymeleaf/tour/th_Insert");
		model.addAttribute("title", "여행지입력");
		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지입력 완료 처리 -> tourDeails페이지로 이동?
	@PostMapping("/tour/tourInsertOK")
	public String tourInsertOK(TourVO vo) {
		log.info("tourInsertOK...");
		log.info("vo:{}", vo);

		int result = service.tourInsertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:tourInsert";
		} else {
			return "redirect:tourInsert";
		}
	}

	// 여행지 정보 수정 페이지 이동
	@GetMapping("/tour/tourUpdate")
	public String tourUpdate(TourVO vo, Model model) {
		log.info("tourUpdate ...");
		log.info("vo:{}", vo);

		TourVO vo2 = service.tourSelectOne(vo);

		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/tour/th_update");
		model.addAttribute("title", "여행지 정보수정");
		return "thymeleaf/th_tourLayout_main";
	}

//	// 여행지 정보 검색 ?details랑 겹치나 확인
//		@GetMapping("/tour/tourSelectOne")
//		public String tourSelectOne(TourVO vo, Model model) {
//			log.info("tourSelectOne ...");
//			log.info("vo:{}", vo);
//
//			TourVO vo2 = service.tourSelectOne(vo);
//
//			model.addAttribute("vo2", vo2);
//
//			model.addAttribute("content", "thymeleaf/tour/th_update");
//			model.addAttribute("title", "여행지 상세정보");
//			return "thymeleaf/tour/th_tourLayout_main";
//		}

	// 여행지 정보 수정 완료처리 -> tourDeails페이지로 이동?
	@PostMapping("/tour/tourUpdateOK")
	public String tourUpdateOK(TourVO vo) {
		log.info("tourUpdateOK ...");

		log.info("vo:{}", vo);

		int result = service.tourUpdateOK(vo);
		log.info("result:{}", result);

		return "redirect:tourSelectOne?tourNum=" + vo.getTourNum();

	}

	// 여행지 정보 삭제 페이지
	@GetMapping("/tour/tourDelete")
	public String tourDelete(Model model) {
		log.info("tourDelete...");

		model.addAttribute("content", "thymeleaf/tour/th_delete");
		model.addAttribute("title", "회원삭제");
		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지 정보 삭제완료 처리 -> selectAll로 돌아가기
	@PostMapping("/tour/tourDeleteOK")
	public String tourDeleteOK(TourVO vo) {
		log.info("tourDeleteOK ...");
		log.info("vo:{}", vo);

		int result = service.tourDeleteOK(vo);
		log.info("result:{}", result);

		return "redirect:tourSelectAll";
	}

	// 여행지 목록
//	@GetMapping("/tour/tourSelectAll")
//	public String tourSelectAll(@RequestParam(defaultValue = "1") int cpage,
//			@RequestParam(defaultValue = "9") int pageBlock, Model model) {
//		log.info("tourSelectAll ...");
//		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
//
//		List<TourVO> vos = service.tourSelectAll(cpage, pageBlock);
//		model.addAttribute("vos", vos);
//
//		// tour테이블에 들어있는 모든여행지수는 몇개?
//		int total_rows = service.getTotalRows();
//		log.info("total_rows:" + total_rows);
//
//		int totalPageCount = 1;
//		if (total_rows / pageBlock == 0) {
//			totalPageCount = 1;
//		} else if (total_rows % pageBlock == 0) {
//			totalPageCount = total_rows / pageBlock;
//		} else {
//			totalPageCount = total_rows / pageBlock + 1;
//		}
//		// 페이지 링크 몇개?
//		log.info("totalPageCount:" + totalPageCount);
//		model.addAttribute("totalPageCount", totalPageCount);
////		model.addAttribute("totalPageCount", 10);//테스트용
//
//		model.addAttribute("content", "thymeleaf/tour/th_selectAll");
//		model.addAttribute("title", "여행지목록");
//		return "thymeleaf/tour/th_tourLayout_main";
//	}

	// 여행지 목록 검색
//	@GetMapping("/tour/tourSearchList")
//	public String tourSearchList(@RequestParam(defaultValue = "1") int cpage,
//			@RequestParam(defaultValue = "9") int pageBlock, String searchKey, String searchWord, Model model) {
//		log.info("tourSearchList ...");
//		log.info("searchKey:{}", searchKey);
//		log.info("searchWord:{}", searchWord);
//		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
//
////		List<MemberVO> vos = service.searchList(searchKey,searchWord);
//		List<TourVO> vos = service.searchListPageBlock(searchKey, searchWord, cpage, pageBlock);
//
//		model.addAttribute("vos", vos);
//
//		// 키워드검색 모든여행지는 몇개?
//		int total_rows = service.getSearchTotalRows(searchKey, searchWord);
//		log.info("total_rows:" + total_rows);
//
//		int totalPageCount = 1;
//		if (total_rows / pageBlock == 0) {
//			totalPageCount = 1;
//		} else if (total_rows % pageBlock == 0) {
//			totalPageCount = total_rows / pageBlock;
//		} else {
//			totalPageCount = total_rows / pageBlock + 1;
//		}
//		// 페이지 링크 몇개?
//		model.addAttribute("totalPageCount", totalPageCount);
////		model.addAttribute("totalPageCount", 10);//테스트용
//
//		model.addAttribute("content", "thymeleaf/tour/th_selectAll");
//		model.addAttribute("title", "회원목록");
//		return "thymeleaf/tour/th_tourLayout_main";
//
//	}

	@GetMapping(value = "/tour")
	public String tourMain(Model model, @ModelAttribute("searchVO") TourVO searchVO) throws Exception {
		log.info("새로운");
		// 페이지 설정
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(9); // 한 페이지에 몇개까지 나타날지 설정
		paginationInfo.setPageSize(searchVO.getPageSize());

//		if (paginationInfo.getFirstRecordIndex() > 0) {
//			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//
//		} else {
//			searchVO.setFirstIndex(1);
//		}
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 총 갯수
//		int totalCount = service.selectListTotalCount(searchVO);
//		paginationInfo.setTotalRecordCount(totalCount);
		// 투어리스트
//		List<TourVO> tourVOList = service.selectTourListWithPaging(searchVO);
		List<TourVO> tourVOList = service.selectTourViewTopListWithPaging(searchVO);

		// best 여행지
		List<TourVO> vos2 = service.tourSelectAllTop(searchVO);
		model.addAttribute("vos2", vos2);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("tourListVO", tourVOList);
//		model.addAttribute("totalCount", totalCount);
//		log.info("갯수:{}", tourVOList);
		log.info("갯수: {}", tourVOList.size());


		model.addAttribute("content", "thymeleaf/tour/th_tourMain");
		model.addAttribute("title", "여행지");

		// 여행지 주소 ENUM
		model.addAttribute("regions", TourRegionTypeEnum.values());
		return "thymeleaf/tour/th_tourLayout_main";
	}

	@GetMapping(value = "/tour/tourSelectAll")
	public String tourSelectAll(Model model, @ModelAttribute("searchVO") TourVO searchVO) throws Exception {
		log.info("확인");
////		// 페이지 설정
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(9); // 한 페이지에 몇개까지 나타날지 설정
		paginationInfo.setPageSize(searchVO.getPageSize());
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
//		if (paginationInfo.getFirstRecordIndex() > 0) {
//			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//
//		} else {
//			searchVO.setFirstIndex(1);
//		}

		// 총 갯수
		int totalCount = service.selectListTotalCount(searchVO);
		paginationInfo.setTotalRecordCount(totalCount);
		// 투어리스트
		List<TourVO> tourVOList = service.selectTourListWithPaging(searchVO);
		log.info("tourListVO: " + tourVOList);

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("tourListVO", tourVOList);
		model.addAttribute("totalCount", totalCount);
		log.info("갯수:{}", totalCount);
		model.addAttribute("content", "thymeleaf/tour/th_selectAll");
		model.addAttribute("title", "여행지");

		// 여행지 주소 ENUM
		model.addAttribute("regions", TourRegionTypeEnum.values());
		return "thymeleaf/tour/th_tourLayout_main";
	}

}
