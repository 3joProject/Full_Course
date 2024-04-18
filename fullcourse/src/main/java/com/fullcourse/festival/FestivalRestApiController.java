package com.fullcourse.festival;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullcourse.tour.TourService;
import com.fullcourse.tour.TourVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class FestivalRestApiController {
Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
    @Autowired
    private FestivalService service;
    
    @RequestMapping("/festival2")
	public String getFestivalList(@RequestBody FestivalVO vo) {
	
//		System.out.println("너의 데이터" +alldata.toString()); //->객체로 변환
		
//		System.out.println("전송받은 데이터 크기: " + alldata.length());
	    System.out.println("전송받은 데이터 내용: " + vo);
//		String[] arr = gson.fromJson(alldata, String[].class);
//		
//	    
//		System.out.println("길이2:"+arr.length);
//		for (int i = 0; i < arr.length; i++) {
//			log.info(arr[i]);
//			System.out.println("되냐?"+arr[i]);
//		}
//		 // 변환된 배열을 순회하며 로그 출력
//        for (String contentId : arr) {
//          System.out.println("Received contentId: " + contentId);
//       }
////		
        System.out.println("Received data from client: " + vo);
        // 서비스를 통해 데이터베이스에 저장
        service.festivalInsertOK(vo);


		
		return "호출완료!";
	}
}
