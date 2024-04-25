package com.fullcourse.tour;

import java.util.List;
import java.util.Map;

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

//      
		return "호출완료!";
	}

	@RequestMapping("/tour/updateThumbUp")
	public TourVO updateThumbUp(@RequestBody TourVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);
		
		  // 클라이언트로부터 받은 tourNum을 사용하여 해당하는 여행 정보를 조회합니다.
        TourVO vo2 = new TourVO();
        vo2.setTourNum(vo.getTourNum());
        log.info("vo2: {}", vo2.getTourNum()); // 로그로 확인
        TourVO tourVO = service.tourSelectOne(vo2);
        log.info("tour: {}", tourVO); // 로그로 확인

		if (tourVO != null) {
			// TourVO를 업데이트하거나 다른 작업 수행
			// 예를 들어, tourThumbUp 값을 증가시킴
			tourVO.setTourThumbUp(tourVO.getTourThumbUp() + 1);
			  log.info("tour: {}", tourVO); // 로그로 확인
			service.updateLikeCount(tourVO);

			return tourVO;
		} else {
			return null;
		}

	}
	
	
//	@RequestMapping("/tour/searchRegion")
//	public List<TourVO> searchRegion(@RequestBody Map<String, String> requestData) {
//
//		System.out.println("전송받은 데이터 내용: " + requestData);
//		System.out.println("Received data from client: " + requestData);
//		// 서비스를 통해 데이터베이스에 저장
//		//likeService.tourLikeInsertOK(vo);
//
//		int cpage = Integer.parseInt(requestData.get("cpage"));
//		int pageBlock = Integer.parseInt(requestData.get("pageBlock"));
//		String searchKey = requestData.get("searchKey");
//		String searchWord = requestData.get("searchWord");
//		List<TourVO> vos = service.searchListAddressPageBlock(searchKey,searchWord,cpage,pageBlock); 
//		return vos;
//	}
	
	@RequestMapping("/tour/decreaseThumbUp")
	public String decreaseThumbUp(@RequestBody TourLikeVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);
		// 서비스를 통해 데이터베이스에 저장
		likeService.tourLikeDeleteOK(vo);

//      
		return "호출완료!";
	}

	@RequestMapping("/tour/updateThumbUpDecrease")
	public TourVO updateThumbUpDecrease(@RequestBody TourVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);
		
		  // 클라이언트로부터 받은 tourNum을 사용하여 해당하는 여행 정보를 조회합니다.
        TourVO vo2 = new TourVO();
        vo2.setTourNum(vo.getTourNum());
        log.info("vo2: {}", vo2.getTourNum()); // 로그로 확인
        TourVO tourVO = service.tourSelectOne(vo2);
        log.info("tour: {}", tourVO); // 로그로 확인

		if (tourVO != null) {
			// TourVO를 업데이트하거나 다른 작업 수행
			// 예를 들어, tourThumbUp 값을 감소시킴
			tourVO.setTourThumbUp(tourVO.getTourThumbUp() - 1);
			  log.info("tour: {}", tourVO); // 로그로 확인
			service.updateLikeCount(tourVO);

			return tourVO;
		} else {
			return null;
		}

	}

}
