package com.fullcourse.tour;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

	// application.properties 변수를 DI
	@Value("${file.dir}")
	String realPath;

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
			// 로그인check start
			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);
			// 로그인check end
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
	public String tourInsertOK(@ModelAttribute TourVO vo, @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		log.info("tourInsertOK...");
		log.info("vo:{}", vo);

//		이미지처리
		log.info(realPath);

		String originName = vo.getFile().getOriginalFilename();

		log.info("getOriginalFilename:{}", originName);

		if (originName.length() == 0) {
			vo.setTourImg("default.png"); // 이미지 선택없이 처리할때

		} else {
			String tourImgName = "img_" + System.currentTimeMillis()
					+ originName.substring(originName.lastIndexOf("."));

			vo.setTourImg(tourImgName);
			File uploadFile = new File(realPath, tourImgName);
//			vo.getFile().transferTo(uploadFile);
			file.transferTo(uploadFile);

			//// create thumbnail image/////////
			BufferedImage original_buffer_img = ImageIO.read(uploadFile);
			BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = thumb_buffer_img.createGraphics();
			graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

			File thumb_file = new File(realPath, "thumb_" + tourImgName);

			ImageIO.write(thumb_buffer_img, tourImgName.substring(tourImgName.lastIndexOf(".") + 1), thumb_file);
		}
//		이미지처리

		int result = service.tourInsertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:/admin/tour/selectAll";
		} else {
			return "redirect:/admin/tour/selectAll";
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
		model.addAttribute("title", "여행지삭제");
		return "thymeleaf/tour/th_tourLayout_main";
	}

	// 여행지 정보 삭제완료 처리 -> selectAll로 돌아가기
	@PostMapping("/tour/tourDeleteOK")
	public String tourDeleteOK(TourVO vo) {
		log.info("tourDeleteOK ...");
		log.info("vo:{}", vo);

		int result = service.tourDeleteOK(vo);
		log.info("result:{}", result);

		return "redirect:/admin/tour/selectAll";
	}

	@GetMapping(value = "/tour")
	public String tourMain(Model model, @ModelAttribute("searchVO") TourVO searchVO, HttpSession session)
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

		// 투어리스트
		List<TourVO> tourVOList = service.selectTourViewTopListWithPaging(searchVO);

		// best 여행지
		List<TourVO> vos2 = service.tourSelectAllTop(searchVO);
		model.addAttribute("vos2", vos2);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("tourListVO", tourVOList);
		log.info("갯수: {}", tourVOList.size());

		model.addAttribute("content", "thymeleaf/tour/th_tourMain");
		model.addAttribute("title", "여행지");

		// 여행지 주소 ENUM
		model.addAttribute("regions", TourRegionTypeEnum.values());
		return "thymeleaf/tour/th_tourLayout_main";
	}

	@GetMapping(value = "/tour/tourSelectAll")
	public String tourSelectAll(Model model, @ModelAttribute("searchVO") TourVO searchVO, HttpSession session)
			throws Exception {
		log.info("확인");

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
////		// 페이지 설정
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(9); // 한 페이지에 몇개까지 나타날지 설정
		paginationInfo.setPageSize(searchVO.getPageSize());
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (paginationInfo.getFirstRecordIndex() > 0) {
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());

		} else {
			searchVO.setFirstIndex(0);
		}

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
