package com.fullcourse.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TourRestApiController {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
    @Autowired
    private TourService service;
	
//	private final String apiKey ="CJE9zLAYFCFIq%2BtV%2BN3i4jrz3a4iOReS264F9RkE8ZibRrVIhLRgEshj4jchWX4ntU0St0uA5p2LPt%2FStMMQJg%3D%3D";
//	
//	// 키워드 검색 주소
//	private String apiAddress = "http://apis.data.go.kr/B551011/KorService1/searchKeyword1";
//	// 페이지 번호
//	private int pageNo = 1;
//	// 호출 개수
//	private int numOfRows = 10;
//	// 컨텐츠 타입. 12번은 관광지
//	private int contentTypeId = 12;
//	// 키워드
//	private String keyWord;
	
	@RequestMapping("/tour2")
	public String getTourList(@RequestBody TourVO vo) {
	
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
        service.tourInsertOK(vo);


		
		return "호출완료!";
	}
}
