package com.fullcourse.product;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullcourse.member.MemberVO;
import com.fullcourse.route.RouteVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Value("${file.dir}")
	String realPath;
	
	// 로그인 상태 확인 메소드
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("member") != null;
    }
    
    @GetMapping("/insert")
    public String insert(Model model, HttpSession session) {
    	
    	
    	if (!isLoggedIn(session)) {
            return "redirect:/login";  // 로그인 페이지 경로를 확인하고 적절히 수정하세요.
        }
    	
    	MemberVO member = (MemberVO) session.getAttribute("member");
    	String memberId = member.getMemberId();
    	
		model.addAttribute("content", "thymeleaf/product/insert");
		model.addAttribute("title", "상품등록");
		List<RouteVO> routes = productService.findAllRoutes(memberId);  // 경로 데이터를 불러오는 서비스 메소드 호출
	    model.addAttribute("routes", routes);
	    
	    boolean loggedIn = true;
        log.info("로그인한사람 아이디:" + member.getMemberId());
        model.addAttribute("loginId", member.getMemberId());
        model.addAttribute("loggedIn", loggedIn);
		
		return "thymeleaf/product/layout_main";
	}
	
	@PostMapping("/insertOK")
	public String insertOK(ProductVO productVO, HttpSession session) throws IllegalStateException, IOException {
		
		MemberVO loggedInMember = (MemberVO) session.getAttribute("member");
	    if (loggedInMember == null) {
	        return "redirect:/login"; // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트
	    }
	    
		productVO.setProductMid(loggedInMember.getMemberId());
		
		log.info(realPath);

		String originName = productVO.getFile().getOriginalFilename();

		log.info("getOriginalFilename:{}", originName);

		if (originName.length() == 0) {
			productVO.setProductImage("default.png");// 이미지선택없이 처리할때
		} else {
			String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));

			productVO.setProductImage(save_name);

			File uploadFile = new File(realPath, save_name);
			productVO.getFile().transferTo(uploadFile);// 원본 이미지저장

			//// create thumbnail image/////////
			BufferedImage original_buffer_img = ImageIO.read(uploadFile);
			BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D graphic = thumb_buffer_img.createGraphics();
			graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null);

			File thumb_file = new File(realPath, "thumb_" + save_name);

			ImageIO.write(thumb_buffer_img, save_name.substring(save_name.lastIndexOf(".") + 1), thumb_file);
		}
		
		int result = productService.insertOK(productVO);
		log.info("result:{}", result);

		if (result == 1) {
			return "redirect:selectAll";
		} else {
			return "redirect:insert";
		}
	}
	
	private boolean isOwner(int productNum, HttpSession session) {
		ProductVO product = productService.selectProductById(productNum);
		MemberVO currentUser = (MemberVO) session.getAttribute("member");
		return product != null && currentUser != null && product.getProductMid().equals(currentUser.getMemberId());
		
	}
	
	@PostMapping("/updateOK")
	public String updateOK(ProductVO productVO, HttpSession session) {
		if (!isOwner(productVO.getProductNum(), session)) {
            return "redirect:/login";
        }
		
		int result = productService.updateOK(productVO);
		log.info("result:{}",result);
		
		return "redirect:sellList";
	}
	
	
	@PostMapping("/deleteOK")
    public String deleteOK(ProductVO productVO, HttpSession session) {
        if (!isOwner(productVO.getProductNum(), session)) {
            return "redirect:/login";  
        }

        int result = productService.deleteOK(productVO);
        log.info("Delete result: {}", result);

        return "redirect:selectAll";
    }
	
	//admin용
	@PostMapping("/admin/deleteOK")
    public String admindeleteOK(ProductVO productVO, HttpSession session) {
      
        int result = productService.deleteOK(productVO);
        log.info("Delete result: {}", result);

        return "redirect:/admin/product/selectAll";
    }
	
	
    @GetMapping("/sellList")
    public String sellList(Model model, HttpServletRequest request) {
    	
    	// 세션에서 member 가져오기
	    HttpSession session = request.getSession();
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    log.info("MemberVO:{}",member);
	    
	    
	    if(member == null) {
	        return "redirect:/login";
	    }else {
		
	    String productMid = member.getMemberId();
	    String memberId = member.getMemberId();
	    	
        List<ProductVO> vos = productService.sellListSelectAll(productMid);
        log.info("vos:{}",vos);
		model.addAttribute("content", "thymeleaf/product/th_sellListMain");
		model.addAttribute("title", "판매내역");
		model.addAttribute("vos", vos);
		
		List<RouteVO> routes = productService.findAllRoutes(memberId);  // 경로 데이터를 불러오는 서비스 메소드 호출
	    model.addAttribute("routes", routes);
	    
	    boolean loggedIn = true;
        log.info("로그인한사람 아이디:" + member.getMemberId());
        model.addAttribute("loginId", member.getMemberId());
        model.addAttribute("loggedIn", loggedIn);
		
		return "thymeleaf/product/th_layout_main";
        }
	}
}