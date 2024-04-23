package com.fullcourse.triprecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullcourse.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TripRecordRestController {

	@Autowired
	private TripRecordService service;


	
	@GetMapping("/api/tripRecord")
	public List<TripRecordVO>  tripRecord(HttpServletRequest request) {
		log.info("tripRecord...");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		int triprecMnum = member.getMemberNum();

		List<TripRecordVO> vos = service.marker(triprecMnum);
		log.info("vos:{}",vos);
		
		
		return vos;
	}

}