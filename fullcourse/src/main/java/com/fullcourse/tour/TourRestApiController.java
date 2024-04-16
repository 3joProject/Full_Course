package com.fullcourse.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullcourse.tour.like.TourLikeService;
import com.fullcourse.tour.like.TourLikeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TourRestApiController {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
    @Autowired
    private TourService service;
    @Autowired
    private TourLikeService likeService;
	
	@RequestMapping("/tour2")
	public String getTourList(@RequestBody TourVO vo) {
	
	    System.out.println("전송받은 데이터 내용: " + vo);
        System.out.println("Received data from client: " + vo);
        // 서비스를 통해 데이터베이스에 저장
        service.tourInsertOK(vo);


		
		return "호출완료!";
	}
	
	@RequestMapping("/tour/increaseThumbUp")
	public String increaseThumbUp(@RequestBody TourLikeVO vo) {
	
	    System.out.println("전송받은 데이터 내용: " + vo);
        System.out.println("Received data from client: " + vo);
        // 서비스를 통해 데이터베이스에 저장
        likeService.tourLikeInsertOK(vo);


		
		return "호출완료!";
	}
}
