package com.fullcourse.festival;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullcourse.festival.like.FestivalLikeService;
import com.fullcourse.festival.like.FestivalLikeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FestivalRestApiController {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	private FestivalService service;
	@Autowired
	private FestivalLikeService likeService;

	@RequestMapping("/festival2")
	public String getFestivalList(@RequestBody FestivalVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
////		
		System.out.println("Received data from client: " + vo);
		// 서비스를 통해 데이터베이스에 저장
		service.festivalInsertOK(vo);

		return "호출완료!";
	}

	@RequestMapping("/festival/increaseThumbUp")
	public String increaseThumbUp(@RequestBody FestivalLikeVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);
		// 서비스를 통해 데이터베이스에 저장
		likeService.festivalLikeInsertOK(vo);

//      
		return "호출완료!";
	}

// 방법1
	@RequestMapping("/festival/updateThumbUp")
	public FestivalVO updateThumbUp(@RequestBody FestivalVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);

		// 클라이언트로부터 받은 festivalNum을 사용하여 해당하는 여행 정보를 조회합니다.
		FestivalVO vo2 = new FestivalVO();
		vo2.setFestivalNum(vo.getFestivalNum());
		log.info("vo2: {}", vo2.getFestivalNum()); // 로그로 확인
		FestivalVO festivalVO = service.festivalSelectOne(vo2);
		log.info("festival: {}", festivalVO); // 로그로 확인

		if (festivalVO != null) {
			// FestivalVO를 업데이트하거나 다른 작업 수행
			// festivalThumbUp 값을 증가시킴
			festivalVO.setFestivalThumbUp(festivalVO.getFestivalThumbUp() + 1);
			log.info("festival: {}", festivalVO); // 로그로 확인
			service.updateLikeCount(festivalVO);

			return festivalVO;
		} else {
			return null;
		}

	}

	// 방법2
//	@RequestMapping("/festival/updateThumbUp")
//	public String updateThumbUp(@RequestBody FestivalVO vo) {
//
//		System.out.println("전송받은 데이터 내용: " + vo);
//		System.out.println("Received data from client: " + vo);
//		service.updateLikeCount(vo);
//
//		return "호출완료";
//
//	}

//	@RequestMapping("/festival/searchRegion")
//	public List<FestivalVO> searchRegion(@RequestBody Map<String, String> requestData) {
//
//		System.out.println("전송받은 데이터 내용: " + requestData);
//		System.out.println("Received data from client: " + requestData);
//		// 서비스를 통해 데이터베이스에 저장
//		//likeService.festivalLikeInsertOK(vo);
//
//		 int cpage = Integer.parseInt(requestData.get("cpage"));
//		    int pageBlock = Integer.parseInt(requestData.get("pageBlock"));
//		String searchKey = requestData.get("searchKey");
//		 String searchWord = requestData.get("searchWord");
//		List<FestivalVO> vos = service.searchListAddressPageBlock(searchKey,searchWord,cpage,pageBlock); 
//		return vos;
//	}
	@RequestMapping("/festival/decreaseThumbUp")
	public String decreaseThumbUp(@RequestBody FestivalLikeVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);
		// 서비스를 통해 데이터베이스에 저장
		likeService.festivalLikeDeleteOK(vo);

//      
		return "호출완료!";
	}

	@RequestMapping("/festival/updateThumbUpDecrease")
	public FestivalVO updateThumbUpDecrease(@RequestBody FestivalVO vo) {

		System.out.println("전송받은 데이터 내용: " + vo);
		System.out.println("Received data from client: " + vo);
		
		  // 클라이언트로부터 받은 festivalNum을 사용하여 해당하는 여행 정보를 조회합니다.
        FestivalVO vo2 = new FestivalVO();
        vo2.setFestivalNum(vo.getFestivalNum());
        log.info("vo2: {}", vo2.getFestivalNum()); // 로그로 확인
        FestivalVO festivalVO = service.festivalSelectOne(vo2);
        log.info("festival: {}", festivalVO); // 로그로 확인

		if (festivalVO != null) {
			// FestivalVO를 업데이트하거나 다른 작업 수행
			// 예를 들어, festivalThumbUp 값을 감소시킴
			festivalVO.setFestivalThumbUp(festivalVO.getFestivalThumbUp() - 1);
			  log.info("festival: {}", festivalVO); // 로그로 확인
			service.updateLikeCount(festivalVO);

			return festivalVO;
		} else {
			return null;
		}

	}
	
}
