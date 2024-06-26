package com.fullcourse.festival;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullcourse.festival.festivalComment.FestivalCommentService;
import com.fullcourse.festival.festivalComment.FestivalCommentVO;
import com.fullcourse.member.MemberVO;
import com.fullcourse.paging.PaginationInfo;
import com.fullcourse.tour.TourRegionTypeEnum;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FestivalController {

	@Autowired
	private FestivalService service;

	@Autowired
	private FestivalCommentService comService;

	// 축제 페이지 메인
//	@GetMapping("/festival")
//	public String festivalMain(@RequestParam(defaultValue = "1") int cpage,
//			@RequestParam(defaultValue = "9") int pageBlock, Model model) {
//		log.info("/festivalMain...");
//
//		List<FestivalVO> vos = service.festivalSelectAll(cpage, pageBlock);
//		
//		//best 축제
//		List<FestivalVO> vos2 = service.festivalSelectAllTop();
//		model.addAttribute("vos2", vos2);
//		model.addAttribute("vos", vos);
//		
//		// 축제테이블에 들어있는 모든 축제는 몇개?
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
//		model.addAttribute("content", "thymeleaf/festival/th_festivalMain");
//		model.addAttribute("title", "축제");
//		return "thymeleaf/festival/th_festivalLayout_main";
//	}

	// 상세정보로 이동
//	@GetMapping("/festival/festivalDetails")
//	public String festivalDetails(FestivalVO vo, Model model) {
//		log.info("festivalDetails...");
//		log.info("vo:{}", vo);
//
//		service.updateviewCount(vo);
//		log.info("updateview..");
//
//		FestivalVO vo2 = service.festivalSelectOne(vo);
//		model.addAttribute("vo2", vo2);
//
//		model.addAttribute("content", "thymeleaf/festival/th_festivalDetails");
//		model.addAttribute("title", "축제상세페이지");
//
//		// 댓글목록 처리로직
//		FestivalCommentVO cvo = new FestivalCommentVO();
//		cvo.setFestivalcoFnum(vo.getFestivalNum());
//		List<FestivalCommentVO> cvos = comService.festivalCommentSelectAll(cvo);
//
//		model.addAttribute("cvos", cvos);
//
//		return "thymeleaf/festival/th_festivalLayout_main";
//	}

	// 상세정보 새로운 버젼
	@GetMapping("/festival/festivalDetails")
	public String festivalDetails(FestivalVO vo, Model model, HttpSession session) {
		log.info("festivalDetails...");
		log.info("vo:{}", vo);

		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {
			session.setAttribute("festivalLikeMemberNum ", member.getMemberNum());
			session.setAttribute("festivalLikeFestivalNum ", vo.getFestivalNum());
			log.info("festivalLikeMemberNum: {}", member.getMemberNum());
			log.info("festivalLikeFestivalNum: {}", vo.getFestivalNum());
			// 로그인check start
			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);
			// 로그인check end
			int likeCount = service.getFestivalLikeCount(member.getMemberNum(), vo.getFestivalNum()); // 회원번호와 글번호를 통해
																										// 좋아요 상태 확인
			log.info("좋아요 체크성공");
			log.info("likeCount: {}", likeCount);
			model.addAttribute("commentWriter", member.getMemberId());
			model.addAttribute("likeCount", likeCount); // 좋아요 상태를 View로 전달
		}
		log.info("Member Number: {}", member);
		service.updateviewCount(vo);
		log.info("updateview..");
		log.info("vo:{}", vo);
		FestivalVO vo2 = service.festivalSelectOne(vo);
		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/festival/th_festivalDetails");
		model.addAttribute("title", "여행상세페이지");

		// 댓글목록 처리로직
		FestivalCommentVO cvo = new FestivalCommentVO();
		cvo.setFestivalcoFnum(vo.getFestivalNum());
		List<FestivalCommentVO> cvos = comService.festivalCommentSelectAll(cvo);

		model.addAttribute("cvos", cvos);

		return "thymeleaf/festival/th_festivalLayout_main";
	}

	// 축제 입력페이지로 이동
	@GetMapping("/festival/festivalInsert")
	public String festivalInsert(Model model) {
		log.info("festivalInsert...");

		model.addAttribute("content", "thymeleaf/festival/th_Insert");
		model.addAttribute("title", "축제입력");
		return "thymeleaf/festival/th_festivalLayout_main";
	}

	// 축제입력 완료 처리 -> festivalDeails페이지로 이동?
	@PostMapping("/festival/festivalInsertOK")
	public String festivalInsertOK(FestivalVO vo) {
		log.info("festivalInsertOK...");
		log.info("vo:{}", vo);

		int result = service.festivalInsertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:/admin/festival/selectAll";
		} else {
			return "redirect:/admin/festival/selectAll";
		}
	}

	// 축제 정보 수정 페이지 이동
	@GetMapping("/festival/festivalUpdate")
	public String festivalUpdate(FestivalVO vo, Model model) {
		log.info("festivalUpdate ...");
		log.info("vo:{}", vo);

		FestivalVO vo2 = service.festivalSelectOne(vo);

		model.addAttribute("vo2", vo2);

		model.addAttribute("content", "thymeleaf/festival/th_update");
		model.addAttribute("title", "축제 정보수정");
		return "thymeleaf/th_festivalLayout_main";
	}

//	// 축제 정보 검색 ?details랑 겹치나 확인
//		@GetMapping("/festival/festivalSelectOne")
//		public String festivalSelectOne(FestivalVO vo, Model model) {
//			log.info("festivalSelectOne ...");
//			log.info("vo:{}", vo);
//
//			FestivalVO vo2 = service.festivalSelectOne(vo);
//
//			model.addAttribute("vo2", vo2);
//
//			model.addAttribute("content", "thymeleaf/festival/th_update");
//			model.addAttribute("title", "축제 상세정보");
//			return "thymeleaf/festival/th_festivalLayout_main";
//		}

	// 축제 정보 수정 완료처리 -> festivalDeails페이지로 이동?
	@PostMapping("/festival/festivalUpdateOK")
	public String festivalUpdateOK(FestivalVO vo) {
		log.info("festivalUpdateOK ...");

		log.info("vo:{}", vo);

		int result = service.festivalUpdateOK(vo);
		log.info("result:{}", result);

		return "redirect:festivalSelectOne?festivalNum=" + vo.getFestivalNum();

	}

	// 축제 정보 삭제 페이지
	@GetMapping("/festival/festivalDelete")
	public String festivalDelete(Model model) {
		log.info("festivalDelete...");

		model.addAttribute("content", "thymeleaf/festival/th_delete");
		model.addAttribute("title", "축제삭제");
		return "thymeleaf/festival/th_festivalLayout_main";
	}

	// 축제 정보 삭제완료 처리 -> selectAll로 돌아가기
	@PostMapping("/festival/festivalDeleteOK")
	public String festivalDeleteOK(FestivalVO vo) {
		log.info("festivalDeleteOK ...");
		log.info("vo:{}", vo);

		int result = service.festivalDeleteOK(vo);
		log.info("result:{}", result);

		return "redirect:/admin/festival/selectAll";
	}

//	// 축제 목록
//	@GetMapping("/festival/festivalSelectAll")
//	public String festivalSelectAll(@RequestParam(defaultValue = "1") int cpage,
//			@RequestParam(defaultValue = "9") int pageBlock, Model model) {
//		log.info("festivalSelectAll ...");
//		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);
//
//		List<FestivalVO> vos = service.festivalSelectAll(cpage, pageBlock);
//		model.addAttribute("vos", vos);
//
//		// festival테이블에 들어있는 모든축제수는 몇개?
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
//		model.addAttribute("content", "thymeleaf/festival/th_selectAll");
//		model.addAttribute("title", "축제목록");
//		return "thymeleaf/festival/th_festivalLayout_main";
//	}

	// 축제 목록 검색
	@GetMapping("/festival/festivalSearchList")
	public String festivalSearchList(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "9") int pageBlock, String searchKey, String searchWord, Model model) {
		log.info("festivalSearchList ...");
		log.info("searchKey:{}", searchKey);
		log.info("searchWord:{}", searchWord);
		log.info("cpage : {}, pageBlock : {}", cpage, pageBlock);

//		List<MemberVO> vos = service.searchList(searchKey,searchWord);
		List<FestivalVO> vos = service.searchListPageBlock(searchKey, searchWord, cpage, pageBlock);

		// 키워드검색 모든축제는 몇개?
		int total_rows = service.getSearchTotalRows(searchKey, searchWord);
		log.info("total_rows:" + total_rows);

		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		// 페이지 링크 몇개?
		model.addAttribute("totalPageCount", totalPageCount);
//		model.addAttribute("totalPageCount", 10);//테스트용

		model.addAttribute("vos", vos);
		model.addAttribute("content", "thymeleaf/festival/th_selectAll");
		model.addAttribute("title", "회원목록");
		return "thymeleaf/festival/th_festivalLayout_main";

	}

	@GetMapping(value = "/festival")
	public String festivalMain(Model model, @ModelAttribute("searchVO") FestivalVO searchVO, HttpSession session)
			throws Exception {
		log.info("새로운");

		// 로그인check start
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		} else {

			log.info("로그인한사람이 없습니다");

		}
		// 로그인check end
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
//		List<FestivalVO> festivalVOList = service.selectFestivalListWithPaging(searchVO);
		List<FestivalVO> festivalVOList = service.selectFestivalViewTopListWithPaging(searchVO);

		// best 여행지
		List<FestivalVO> vos2 = service.festivalSelectAllTop(searchVO);
		model.addAttribute("vos2", vos2);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("festivalListVO", festivalVOList);
//		model.addAttribute("totalCount", totalCount);
//		log.info("축제갯수:{}", totalCount);

		log.info("갯수: {}", festivalVOList.size());
		model.addAttribute("content", "thymeleaf/festival/th_festivalMain");
		model.addAttribute("title", "축제");

		// 여행지 주소 ENUM
		model.addAttribute("regions", TourRegionTypeEnum.values());
		return "thymeleaf/festival/th_festivalLayout_main";
	}

	@GetMapping(value = "/festival/festivalSelectAll")
	public String festivalSelectAll(Model model, @ModelAttribute("searchVO") FestivalVO searchVO, HttpSession session)
			throws Exception {
		log.info("확인2");

		// 로그인check start
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		} else {
			log.info("로그인한사람이 없습니다");
		}
		// 로그인check end
		// 페이지 설정
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
		List<FestivalVO> festivalVOList = service.selectFestivalListWithPaging(searchVO);

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("festivalListVO", festivalVOList);
		model.addAttribute("totalCount", totalCount);
		log.info("축제갯수:{}", totalCount);
		model.addAttribute("content", "thymeleaf/festival/th_selectAll");
		model.addAttribute("title", "축제");

		// 여행지 주소 ENUM
		model.addAttribute("regions", TourRegionTypeEnum.values());
		return "thymeleaf/festival/th_festivalLayout_main";
	}

//	@GetMapping(value = "/festival/festivalSelectMonth")
//	public String festivalSelectMonth(Model model, @ModelAttribute("searchVO") FestivalVO searchVO, HttpSession session)
//			throws Exception {
//		log.info("확인2");
//
//		// 로그인check start
//		MemberVO member = (MemberVO) session.getAttribute("member");
//		if (member != null) {
//
//			boolean loggedIn = true;
//			log.info("로그인한사람 아이디:" + member.getMemberId());
//			model.addAttribute("loginId", member.getMemberId());
//			model.addAttribute("loggedIn", loggedIn);
//
//		} else {
//			log.info("로그인한사람이 없습니다");
//		}
//		// 로그인check end
//		
//		FestivalVO fvo = new FestivalVO();
//		fvo.setFestivalStart(searchVO.getFestivalStart());
//		
//		 // month 값 확인
////	    log.info("클라이언트로부터 전달된 월: {}", month);
//
//	    // festivalStart 필드에 month 값을 할당
////	    searchVO.setFestivalStart(month);		
//		// 페이지 설정
//		PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(9); // 한 페이지에 몇개까지 나타날지 설정
//		paginationInfo.setPageSize(searchVO.getPageSize());
//		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
////		if (paginationInfo.getFirstRecordIndex() > 0) {
////			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
////
////		} else {
////			searchVO.setFirstIndex(1);
////		}
//
//		// 총 갯수
//		int totalCount = service.selectListTotalCount(searchVO);
//		paginationInfo.setTotalRecordCount(totalCount);
//		// 투어리스트
////		List<FestivalVO> festivalVOList = service.selectFestivalListWithPagingByMonth(searchVO);
//
//		model.addAttribute("paginationInfo", paginationInfo);
////		model.addAttribute("festivalListVO", festivalVOList);
//		model.addAttribute("totalCount", totalCount);
//		log.info("축제갯수:{}", totalCount);
//		model.addAttribute("content", "thymeleaf/festival/th_selectAllbyMonth");
//		model.addAttribute("title", "축제");
//
//		// 여행지 주소 ENUM
//		return "thymeleaf/festival/th_festivalLayout_main";
//	}

	@GetMapping(value = "/festival/festivalSelectMonth")
	public String festivalSelectMonth(Model model,
			@ModelAttribute("searchVO") FestivalVO searchVO, HttpSession session) throws Exception {
		// 세션에서 로그인한 사용자 확인
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member != null) {
			boolean loggedIn = true;
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);
		}

		  // 클라이언트로부터 받은 월 값을 FestivalVO 객체의 festivalStart 속성으로 설정
	    if (searchVO.getFestivalStart() == null || searchVO.getFestivalStart().isEmpty()) {
	        String defaultMonth = "202401"; // 기본으로 선택할 월 (예: 1월)
	        searchVO.setFestivalStart(defaultMonth);
	    }

		// 월별 축제 목록 가져오기
		List<FestivalVO> festivalList = service.selectFestivalListWithPagingByMonth(searchVO);
//		log.info("festivalList:{}",festivalList);

		// 페이지 설정
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(9); // 한 페이지에 몇개까지 나타날지 설정
		paginationInfo.setPageSize(searchVO.getPageSize());
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

//
//		if (paginationInfo.getFirstRecordIndex() > 0) {
//			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//
//		} else {
//			searchVO.setFirstIndex(0);
//		}
		// 총 갯수
		int totalCount = service.selectListTotalCount(searchVO);
		paginationInfo.setTotalRecordCount(totalCount);
		log.info("festivalList:{}",festivalList.size());
		model.addAttribute("festivalList", festivalList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("content", "thymeleaf/festival/th_selectAllbyMonth");
		model.addAttribute("title", "축제");

		return "thymeleaf/festival/th_festivalLayout_main";
	}

}
