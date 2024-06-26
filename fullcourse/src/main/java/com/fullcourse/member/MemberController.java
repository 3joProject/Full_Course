package com.fullcourse.member;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullcourse.seller.sellerReview.SellerReviewVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
public class MemberController {

	@Autowired
	private MemberService memberService;

	// application.properties 변수를 DI
		@Value("${file.dir}")
		String realPath;
	
	@GetMapping("/memberinsert")
	public String insert(Model model) {

		log.info("/memberinsert");

		model.addAttribute("title", "회원가입");

		return "thymeleaf/member/insert";
	}

	@PostMapping("/memberinsertOK")
	public String insertOK(MemberVO memberVO) {

		log.info("/memberinsertOK...");

		int result = memberService.insertOK(memberVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:login";
		} else {
			return "redirect:insert";
		}
	}

	@ModelAttribute("memberVO")
	public MemberVO memberVO() {
		return new MemberVO(); // 또는 필요한 로직으로 초기화된 객체
	}

	// 로그인 폼 페이지
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "로그인");
		return "thymeleaf/member/login";
	}

	@PostMapping("/login")
	public String loginProcess(String memberId, String memberPw, HttpSession session,
			RedirectAttributes redirectAttributes) {
		log.info("로그인 시도: {}", memberId);
		MemberVO member = memberService.login(memberId, memberPw);

		if (member != null) {
			session.setAttribute("memberNum", member.getMemberNum());
			session.setAttribute("member", member);
			session.setMaxInactiveInterval(3600);
			log.info("로그인 성공: {}", memberId);
			return "redirect:/index";
		} else {
			log.error("로그인 실패: {}", memberId);
			redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 옳지 않습니다");
			return "redirect:/login";
		}
	}

	@GetMapping("/mypage")
	public String myPage(HttpSession session, Model model) {
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member == null) {
			return "redirect:/login"; // 로그인이 되어있지 않다면 로그인 페이지로 리다이렉트
		}

		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		}
		MemberVO member2 = memberService.selectOne(member);
		
		model.addAttribute("member2", member2);
		model.addAttribute("memberVO", member);
		model.addAttribute("content", "thymeleaf/member/mypage");
//        return "thymeleaf/member/mypage";  // 마이페이지 뷰로 이동
		return "thymeleaf/member/th_myPageLayout_main";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/login"; // 로그인 페이지로 리다이렉트
	}

	// 판매자 상세 정보 페이지
	@GetMapping("/seller/{sellerId}")
	public String sellerDetail(@PathVariable String sellerId, Model model, HttpServletRequest request) {
		log.info("판매자 상세 정보 조회: {}", sellerId);
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		// 판매자 정보 조회
		MemberVO seller = memberService.getMemberById(sellerId);
		if (member == null) {
			return "redirect:/login";
		}

		if (member != null) {

			boolean loggedIn = true;
			log.info("로그인한사람 아이디:" + member.getMemberId());
			model.addAttribute("loginId", member.getMemberId());
			model.addAttribute("loggedIn", loggedIn);

		}

        List<SellerReviewVO> vos = memberService.reviewSelectAll(sellerId);
		log.info("vos:{}",vos);
	    log.info("MemberVO:{}",member);

		model.addAttribute("vos",vos);
        model.addAttribute("seller", seller);
        model.addAttribute("member", member);
        return "thymeleaf/member/sellerDetail"; // 판매자 상세 정보 페이지로 이동
    }



	@GetMapping("/member/updateMemberInfo")
	public String updateMemberInfoForm(HttpSession session, Model model) {
		Integer memberNum = (Integer) session.getAttribute("memberNum");
		log.info("Member Number: {}", memberNum);
		if (memberNum == null) {
			log.info("Member Number not found in session, redirecting to login page.");
			return "redirect:/login"; // 로그인 페이지로 리디렉트
		}

		MemberVO memberVO = memberService.getMemberByNum(memberNum);
		if (memberVO == null) {
			log.warn("No member found with memberNum: {}", memberNum);
			return "redirect:/mypage"; // 오류 페이지 또는 적절한 메시지 페이지로 리다이렉트
		}

		model.addAttribute("memberVO", memberVO);
		return "thymeleaf/member/updateMemberInfo";
	}

	@PostMapping("/updateMemberInfo")
	public String updateMemberInfo(@ModelAttribute MemberVO memberVO,
	                               @RequestParam("file") MultipartFile file,
	                               RedirectAttributes redirectAttributes) throws IOException {

//	    // 이미지 파일 저장 로직
//	    if (!file.isEmpty()) {
//	        try {
//	            // 이미지 파일 저장 경로 설정 (예: /resources/images/profile/)
//	            Path uploadPath = Paths.get("C:\\Users\\moon\\AppData\\Local\\Temp\\tomcat.8805.10212324049996384096\\work\\Tomcat\\localhost\\ROOT\\path\\to\\upload");
//
//	            // 디렉토리가 존재하지 않으면 생성
//	            if (!Files.exists(uploadPath)) {
//	                Files.createDirectories(uploadPath);
//	            }
//
//	            // 이미지 파일명 생성 (예: profileImage_1234567890.jpg)
//	            String fileName = "profileImage_" + System.currentTimeMillis() + "." + file.getOriginalFilename().split("\\.")[1];
//	            Path filePath = uploadPath.resolve(fileName);
//
//	            // 이미지 파일 저장
//	            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//	            // 회원 정보에 이미지 파일명 설정
//	            memberVO.setMemberImg(fileName);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            redirectAttributes.addFlashAttribute("message", "이미지 업로드에 실패했습니다.");
//	            return "redirect:/member/updateMemberInfoForm";
//	        }
//	    }
		
		
		//이미지처리
		log.info(realPath);

		String originName = memberVO.getFile().getOriginalFilename();

		log.info("getOriginalFilename:{}", originName);

		if (originName.length() == 0) {
			memberVO.setMemberImg("default.png"); // 이미지 선택없이 처리할때

		} else {
			String memberImg = "img_" + System.currentTimeMillis()
					+ originName.substring(originName.lastIndexOf("."));

			memberVO.setMemberImg(memberImg);
			File uploadFile = new File(realPath, memberImg);
//			vo.getFile().transferTo(uploadFile);
			file.transferTo(uploadFile);

			//// create thumbnail image/////////
			BufferedImage original_buffer_img = ImageIO.read(uploadFile);
			BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = thumb_buffer_img.createGraphics();
			graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

			File thumb_file = new File(realPath, "thumb_" + memberImg);

			ImageIO.write(thumb_buffer_img, memberImg.substring(memberImg.lastIndexOf(".") + 1), thumb_file);
		}
//		이미지처리

	    // 회원 정보 업데이트 로직
	    memberService.updateMember(memberVO);
	    redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");

	    return "redirect:/mypage";
	}

//	admin페이지 멤버 삭제 
	@GetMapping("/member/memberDelete")
	public String memberDelete(Model model) {
		log.info("memberDelete...");

		model.addAttribute("content", "thymeleaf/member/th_delete");
		model.addAttribute("title", "회원삭제");
		return "thymeleaf/member/th_memberLayout_main";
	}

//	admin페이지 멤버 삭제
	@PostMapping("/member/memberDeleteOK")
	public String memberDeleteOK(MemberVO vo) {
		log.info("memberDeleteOK ...");
		log.info("vo:{}", vo);

		int result = memberService.memberDeleteOK(vo);
		log.info("result:{}", result);

		return "redirect:/admin/member/selectAll";
	}
}
