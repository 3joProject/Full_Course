package com.fullcourse.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullcourse.member.mapper.MemberMapper;
import com.fullcourse.seller.sellerReview.SellerReviewVO;

@Service
public class MemberService {

	//@Repository를 DI하지않고 대신 @Mapper클래스를 DI합니다.
	@Autowired
	private MemberMapper mapper;
	

	public int insertOK(MemberVO vo) {
		return mapper.insertOK(vo);
	}

	public int updateOK(MemberVO vo) {
		return mapper.updateOK(vo);
	}

	public int deleteOK(MemberVO vo) {
		return mapper.deleteOK(vo);
	}

	public List<MemberVO> selectAll() {
		return mapper.selectAll();
	}
	public List<MemberVO> selectAllPageBlock(int cpage,int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.selectAllPageBlock(map);
	}

	public MemberVO selectOne(MemberVO vo) {
		return mapper.selectOne(vo);
	}
	
	public List<MemberVO> searchList(String searchKey,String searchWord) {
		
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("id")) {
			return mapper.searchListID(map);
		}else {
			return mapper.searchListNAME(map);
		}
	}

	public List<MemberVO> searchListPageBlock(
			String searchKey, String searchWord, 
			int cpage, int pageBlock) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		if(searchKey.equals("id")) {
			return mapper.searchListID_PAGE(map);
		}else {
			return mapper.searchListNAME_PAGE(map);
		}
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("id")) {
			return mapper.search_total_rows_id(map);
		}else {
			return mapper.search_total_rows_name(map);
		}
	}
	// 로그인 메소드 추가
    public MemberVO login(String memberId, String memberPw) {
        return mapper.login(memberId, memberPw);
    }
    
    public MemberVO getMemberById(String memberId) {
        return mapper.findMemberById(memberId);
    }
    public void followMember(String memberId, String followerId) {
        // 이 메소드는 판매자의 팔로워 수를 증가시키는 로직을 포함해야 합니다.
        mapper.increaseFollowerCount(memberId);
        // 팔로워 관계를 저장하는 로직을 추가할 수 있습니다.
        mapper.addFollower(memberId, followerId);
    }
    public List<MemberVO> getFollowingByMemberId(String memberId) {
        return mapper.findFollowingByMemberId(memberId);
    }
    
    
    public void updateMember(MemberVO memberVO) {
        mapper.updateMember(memberVO);
    }

    
    public MemberVO getMemberByNum(int memberNum) {
        return mapper.getMemberByNum(memberNum);
    }

	public List<SellerReviewVO> reviewSelectAll(String sellerId) {
		return mapper.reviewSelectAll(sellerId);
	}

}
