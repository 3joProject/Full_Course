package com.fullcourse.triprecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TripRecordController {

	@Autowired
	private TripRecordService service;

	@Value("${file.dir}")
	String realPath;

	@GetMapping("/tripRecord")
	public String tripRecord(@RequestParam(name = "cpage", defaultValue = "1") int cpage,
			@RequestParam(name = "pageBlock", defaultValue = "10") int pageBlock, Model model,
			HttpServletRequest request) {
		log.info("tripRecord");

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		log.info("MemberVO:{}", member);

		if (member == null) {
			log.info("로그인한사람이 없습니다");
			return "redirect:/login";
		} else {
			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

			int tripRecMnum = member.getMemberNum();
			log.info("tripRecMnum:{}", tripRecMnum);

			List<TripRecordVO> vos = service.selectAll(cpage, pageBlock, tripRecMnum);
			log.info("vos:{}", vos);

			model.addAttribute("vos", vos);

			int total_rows = service.getTotalRows(tripRecMnum);
			log.info("total_rows:{} ", total_rows);

			int totalPageCount = 1;
			if (total_rows / pageBlock == 0) {
				totalPageCount = 1;
			} else if (total_rows % pageBlock == 0) {
				totalPageCount = total_rows / pageBlock;
			} else {
				totalPageCount = total_rows / pageBlock + 1;
			}
			log.info("totalPageCount:{} ", totalPageCount);

			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("content", "thymeleaf/tripRecord/th_tripRecordMain");
			model.addAttribute("title", "여행기록");

			return "thymeleaf/tripRecord/th_tripRecordLayout_main";
		}
	}

	@PostMapping("/tripRecord/insertOK")
	public RedirectView tripRecordInsertOK(TripRecordVO vo, HttpServletRequest request, @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		log.info("tripRecordinsertOK");
		log.info("vo:{}", vo);

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		log.info("MemberVO:{}", member);

		if (member == null) {
			return new RedirectView("/login");
		} else {
			log.info(realPath);

			String originName = vo.getFile().getOriginalFilename();
			log.info(originName);
			if (originName.length() == 0) {
				vo.setTriprecImage("default.png"); // 이미지선택없이 처리할때
			} else {
				String saveName = "img_" + System.currentTimeMillis()
						+ originName.substring(originName.lastIndexOf("."));

				vo.setTriprecImage(saveName);
				log.info(saveName);
				File uploadFile = new File(realPath, saveName);
				vo.getFile().transferTo(uploadFile);// 원본 이미지저장
			}
			vo.setTriprecMnum(member.getMemberNum());
			log.info("vo:{}", vo);
			int result = service.insertOK(vo);
			log.info("result:{}", result);

			return new RedirectView("/tripRecord");
		}

	}

	@PostMapping("/tripRecord/updateOK")
	public RedirectView tripRecordUpdateOK(TripRecordVO vo) {
		log.info("tripRecordUpdateOK");
		log.info("vo:{}", vo);

		int result = service.updateOK(vo);
		log.info("result:{}", result);

		return new RedirectView("/tripRecord");

	}

	@GetMapping("/tripRecord/deleteOK")
	public RedirectView tripRecordDeleteOK(TripRecordVO vo) {
		log.info("tripRecordDeleteOK");
		log.info("vo:{}", vo);

		int result = service.deleteOK(vo);
		log.info("result:{}", result);

		return new RedirectView("/tripRecord");

	}

}
