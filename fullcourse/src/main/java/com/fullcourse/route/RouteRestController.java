package com.fullcourse.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/route")
public class RouteRestController {

	@Autowired
	private RouteService service;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody RouteVO vo) {
		log.info("save____________________");
		log.info("Received RouteVO: {}", vo); // VO를 로그에 출력
		try {

			service.save(vo);

			return ResponseEntity.ok("{\"message\": \"con여행 일정이 저장되었습니다.\"}");

		} catch (Exception e) {
			log.info("not");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"message\": \"con여행 일정 저장에 실패했습니다.\"}");
		}
	}

	@GetMapping("/gettour")
	public ResponseEntity<List<TourVO>> getTour() {
		// 여행 데이터를 가져오는 로직을 구현
		List<TourVO> tourList = service.getTours();
		return ResponseEntity.ok(tourList);

	}

	@GetMapping("/getroute")
	public ResponseEntity<List<RouteVO>> getRoute() {
		// 여행 데이터를 가져오는 로직을 구현
		List<RouteVO> RouteList = service.getRoute();
		return ResponseEntity.ok(RouteList);

	}

	@DeleteMapping("/deleteAll/{routeName}")
	public ResponseEntity<String> deleteAllRoutes(@PathVariable("routeName") String routeName) {
		// routeName을 기반으로 모든 일정을 삭제하는 서비스 메서드를 호출합니다.
		log.info("deletAll");
		service.deleteAllRoutes(routeName);
		return ResponseEntity.status(HttpStatus.OK).body("여행 일정이 성공적으로 삭제되었습니다.");
	}

}
