package com.fullcourse.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ParkingController {

	@Autowired
	private ParkingService service;

	@GetMapping("/parking")
	public String parking(HttpSession session, Model model) {
		log.info("parking...");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {

            boolean loggedIn = true;
            log.info("로그인한사람 아이디:" + member.getMemberId());
            model.addAttribute("loginId", member.getMemberId());
            model.addAttribute("loggedIn", loggedIn);

        } else {

            log.info("로그인한사람이 없습니다");
        }
		
		return "thymeleaf/parking/parking";
	}

}