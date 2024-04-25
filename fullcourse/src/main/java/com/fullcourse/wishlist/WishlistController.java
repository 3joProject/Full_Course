package com.fullcourse.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fullcourse.festival.FestivalVO;
import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.TourVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WishlistController {

	@Autowired
	private WishlistService service;

	@GetMapping("/wishList")
	public String wishList(@RequestParam(name = "tpage", defaultValue = "1") int tpage,
			@RequestParam(name = "fpage", defaultValue = "1") int fpage,
			@RequestParam(name = "pageBlock", defaultValue = "3") int pageBlock, Model model,
			HttpServletRequest request) {
		log.info("wishList");
		log.info("tpage : {}, fpage : {}, pageBlock : {}", tpage, fpage, pageBlock);

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

			int wishListId = member.getMemberNum();
			log.info("wishListId:{}", wishListId);

			List<WishlistViewVO> vosTour = service.selectAllTour(tpage, pageBlock, wishListId);
			log.info("vosTour:{}", vosTour);
			model.addAttribute("vosTour", vosTour);

			int totalRowsTour = service.getTotalRowsTour(wishListId);
			log.info("totalRowsTour:{}", totalRowsTour);

			int totalPageCountTour = 1;
			if (totalRowsTour / pageBlock == 0) {
				totalPageCountTour = 1;
			} else if (totalRowsTour % pageBlock == 0) {
				totalPageCountTour = totalRowsTour / pageBlock;
			} else {
				totalPageCountTour = totalRowsTour / pageBlock + 1;
			}

			log.info("totalPageCountTour:{} ", totalPageCountTour);

			model.addAttribute("totalPageCountTour", totalPageCountTour);

			List<WishlistViewVO> vosFestival = service.selectAllFestival(fpage, pageBlock, wishListId);

			log.info("vosFestival:{}", vosFestival);

			model.addAttribute("vosFestival", vosFestival);

			int totalRowsFestival = service.getTotalRowsFestival(wishListId);
			log.info("totalRowsFestival:{}", totalRowsFestival);

			int totalPageCountFestival = 1;
			if (totalRowsFestival / pageBlock == 0) {
				totalPageCountFestival = 1;
			} else if (totalRowsFestival % pageBlock == 0) {
				totalPageCountFestival = totalRowsFestival / pageBlock;
			} else {
				totalPageCountFestival = totalRowsFestival / pageBlock + 1;
			}

			log.info("totalPageCountFestival:{} ", totalPageCountFestival);

			model.addAttribute("totalPageCountFestival", totalPageCountFestival);

			model.addAttribute("vosFestival", vosFestival);

//		log.info("vosFestival:{}",vosFestival);
//		
//		List<WishlistViewVO> vosAll = new ArrayList<>();
//		vosAll.addAll(vosTour);
//		vosAll.addAll(vosFestival);
//		
//		log.info("vosAll:{}",vosAll);
//		
//		try {
//			vosAll = vosAll.stream().sorted(Comparator.comparing(WishlistViewVO::getWishListNum).reversed()).
//					collect(Collectors.toList()).subList((cpage-1)*pageBlock, pageBlock);
//		} catch (IndexOutOfBoundsException e) {
//			
//		}
//		
//		log.info("vosAll2:{}",vosAll);
//		
//		model.addAttribute("vosAll",vosAll);
			model.addAttribute("content", "thymeleaf/wishList/th_wishListMain");
			model.addAttribute("title", "즐겨찾기");

			return "thymeleaf/wishList/th_wishListLayout_main";
		}
	}

	@GetMapping("/wishList/insertOK/tour")
	public RedirectView insertOKtour(WishlistVO vo, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		log.info("insertOKtour");
		log.info("vo:{}", vo);

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		log.info("MemberVO:{}", member);

		if (member == null) {
			return new RedirectView("/login");
		} else {

			vo.setWishListId(member.getMemberNum());
			log.info("vo:{}", vo);

			int chkWDuplTour = service.chkWDuplTour(vo);
			log.info("chkWDuplTour:{}", chkWDuplTour);

			if (chkWDuplTour > 0) {
				redirectAttributes.addFlashAttribute("warningMessageTour", "이미 추가된 관광지입니다."); // 경고 메시지 설정
			} else {
				int result = service.insertOKtour(vo);
				log.info("result:{}", result);
			}

			return new RedirectView("/wishList");
		}
	}

	@GetMapping("/wishList/insertOK/festival")
	public RedirectView insertOKfestival(WishlistVO vo, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		log.info("insertOKfestival");
		log.info("vo:{}", vo);

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		log.info("MemberVO:{}", member);

		if (member == null) {
			return new RedirectView("/login");
		} else {

			vo.setWishListId(member.getMemberNum());
			log.info("vo:{}", vo);

			int chkWDuplFestival = service.chkWDuplFestival(vo);
			log.info("chkWDuplFestival:{}", chkWDuplFestival);

			if (chkWDuplFestival > 0) {
				redirectAttributes.addFlashAttribute("warningMessageFestival", "이미 추가된 축제입니다."); // 경고 메시지 설정
			} else {
				int result = service.insertOKfestival(vo);
				log.info("result:{}", result);
			}

			return new RedirectView("/wishList");
		}
	}

	@GetMapping("/wishList/deleteOK")
	public RedirectView deleteOkwishList(WishlistVO vo) {
		log.info("deleteOkwishList");
		log.info("vo:{}", vo);

		int result = service.deleteOK(vo);

		return new RedirectView("/wishList");
	}

}
