package com.fullcourse.triprecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class TripRecordController {

	@Autowired
	private TripRecordService service;

	@GetMapping("/tripRecord")
	public String tripRecord(@RequestParam(name = "cpage", defaultValue = "1") int cpage,
			@RequestParam(name = "pageBlock", defaultValue = "10") int pageBlock, Model model) {
		log.info("tripRecord");

		List<TripRecordVO> vos = service.selectAll(cpage, pageBlock);
		log.info("vos:{}", vos);

		model.addAttribute("vos", vos);

		int total_rows = service.getTotalRows();
		log.info("total_rows:{} ", total_rows);

		int totalPageCount = 1;
		if (total_rows / pageBlock == 0) {
			totalPageCount = 1;
		} else if (total_rows % pageBlock == 0) {
			totalPageCount = total_rows / pageBlock;
		} else {
			totalPageCount = total_rows / pageBlock + 1;
		}
		log.info("totalPageCount:{} ", totalPageCount);

		model.addAttribute("totalPageCount", totalPageCount);

		return "thymeleaf/tripRecord/tripRecordPage";
	}

	@GetMapping("/tripRecord/insert")
	public String tripRecordInsert() {
		log.info("tripRecordInsert");

		return "thymeleaf/tripRecord/tripRecordInsertPage";
	}

	@PostMapping("/tripRecord/insertOK")
	public RedirectView tripRecordInsertOK(TripRecordVO vo) {
		log.info("tripRecordinsertOK");
		log.info("vo:{}", vo);

		int result = service.insertOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return new RedirectView("/tripRecord");
		} else {
			return new RedirectView("/tripRecord/insert");
		}

	}

	@GetMapping("/tripRecord/update")
	public String tripRecordUpdate(TripRecordVO vo, Model model) {
		log.info("tripRecordUpdate");
		log.info("vo:{}", vo);

		TripRecordVO vo2 = service.selectOne(vo);
		log.info("vo2:{}", vo2);

		model.addAttribute("vo2", vo2);

		return "thymeleaf/tripRecord/tripRecordUpdatePage";
	}

	@PostMapping("/tripRecord/updateOK")
	public RedirectView tripRecordUpdateOK(TripRecordVO vo) {
		log.info("tripRecordUpdateOK");
		log.info("vo:{}", vo);

		int result = service.updateOK(vo);
		log.info("result:{}", result);

		if (result == 1) {
			return new RedirectView("/tripRecord");
		} else {
			return new RedirectView("/tripRecord/update");
		}

	}
	
	@GetMapping("/tripRecord/deleteOK")
	public RedirectView tripRecordDeleteOK(TripRecordVO vo) {
		log.info("tripRecordDeleteOK");
		log.info("vo:{}", vo);

		int result = service.deleteOK(vo);
		log.info("result:{}", result);

		return new RedirectView("/tripRecord");

	}

}
