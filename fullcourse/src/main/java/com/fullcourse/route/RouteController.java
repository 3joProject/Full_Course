package com.fullcourse.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullcourse.member.MemberVO;
import com.fullcourse.tour.TourService;
import com.fullcourse.tour.TourVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class RouteController {

//	@Value("${tmap.api.key}")
//	private String tmapApiKey;

	@Autowired
	private TourService toService;
	@Autowired
	private RouteService service;

	@GetMapping("/route")
	public String map(Model model, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    Integer memberNum = (Integer) session.getAttribute("memberNum");
	    
	    if (memberNum == null) {
	        return "redirect:/login"; // 로그인 페이지로 리디렉션
	    }
	    
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    if (member != null) {
	        boolean loggedIn = true;
	        log.info("로그인한사람 아이디:" + member.getMemberId());
	        model.addAttribute("loginId", member.getMemberId());
	        model.addAttribute("loggedIn", loggedIn);
	        
	        log.info("map..");
	        
	        String routeUserId = member.getMemberId();
	        log.info("routeUserId:{}", routeUserId);

	        model.addAttribute("content", "thymeleaf/route/TMAP");
	        model.addAttribute("routeUserId", routeUserId);
	        
	        // index.html 파일을 반환
	        return "thymeleaf/route/th_routeLayout_main"; 
	    }
	    
	    return "redirect:/login"; // 로그인 페이지로 리디렉션
	}


	@GetMapping("/route/list")
	public String tourlist(@RequestParam(defaultValue = "1") int cpage, @RequestParam(defaultValue = "5") int pageBlock,
			Model model) {
		List<RouteVO> routes = service.routeSelectAll(cpage, pageBlock);

		int total_rows = service.getTotalRows();
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
		log.info("totalPageCount:" + totalPageCount);
		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("vos", routes);

		return "thymeleaf/route/list";
	}

	@GetMapping("/route/{routeName}")
	public String selectOne(@PathVariable String routeName, Model model) {
		log.info("routename : {}", routeName);
		List<RouteVO> routes = service.routeSelectOne(routeName);

		model.addAttribute("routes", routes);

		return "thymeleaf/route/selectOne";

	}

	// 여행지입력 완료 처리 -> tourDeails페이지로 이동?
	@PostMapping("/route/tourInsertOK")
	public String tourInsertOK(TourVO vo) {
		log.info("route..tourInsertOK...");
		log.info("vo:{}", vo);

		int result = toService.tourInsertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:/route";
		} else {
			return "redirect:/route";
		}
	}

	@GetMapping("/routeList")
	public String routeList(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int pageBlock, Model model, HttpServletRequest request) {
		log.info("routeList");

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		log.info("MemberVO:{}", member);

		if (member == null) {
			return "redirect:/login";
		} else {

			String routeUserId = member.getMemberId();
			log.info("routeUserId:{}", routeUserId);

			List<RouteVO> routes = service.selectAllRouteList(cpage, pageBlock, routeUserId);

			int total_rows = service.getTotalRowsRouteList(routeUserId);
			log.info("total_rows:" + total_rows);
			log.info("routes {}", routes);
			int totalPageCount = 1;
			if (total_rows / pageBlock == 0) {
				totalPageCount = 1;
			} else if (total_rows % pageBlock == 0) {
				totalPageCount = total_rows / pageBlock;
			} else {
				totalPageCount = total_rows / pageBlock + 1;
			}
			// 페이지 링크 몇개?
			log.info("totalPageCount:" + totalPageCount);
			model.addAttribute("vos", routes);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("title", "가이드북리스트");

			return "thymeleaf/route/mypageRouteList";
		}
	}

	
	@GetMapping("/route/update")
	public String routeUpdate(@RequestParam("routeName") String routeName, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		log.info("MemberVO:{}", member);
		
		String routeUserId = member.getMemberId();
		log.info("routeUserId:{}", routeUserId);


	    // routeName을 이용하여 필요한 데이터를 가져오거나 처리하는 작업 수행
		
		log.info("routename : {}", routeName);
		List<RouteVO> routes = service.routeUpdate(routeName);
		
		log.info("routes : {}", routes);
	    // 가져온 데이터를 모델에 추가
		model.addAttribute("routes", routes);
		
		model.addAttribute("routeUserId", routeUserId);
		
	    // 수정 페이지의 경로를 반환
	    return "thymeleaf/route/update";
	}
	
	@PostMapping("route/mypageDelete")
	public String deleteOK(RouteVO vo, RedirectAttributes redirectAttributes) {
	    int result = service.mypageDeleteAll(vo);
	    if (result == 1) {
	        redirectAttributes.addAttribute("success", true);
	    } else {
	        redirectAttributes.addAttribute("success", false);
	    }
	    return "redirect:/mypage";
	}
	
}
