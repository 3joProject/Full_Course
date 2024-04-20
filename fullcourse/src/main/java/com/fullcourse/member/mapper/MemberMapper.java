package com.fullcourse.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fullcourse.member.MemberVO;
import com.fullcourse.seller.sellerReview.SellerReviewVO;

@Mapper
public interface MemberMapper {

	// 추상메소드명(insertOK)과 mapper.xml(id="insertOK" 같게 해준다.)
	public int insertOK(MemberVO vo);

	public int updateOK(MemberVO vo);

	public int deleteOK(MemberVO vo);

	public List<MemberVO> selectAll();

	public MemberVO selectOne(MemberVO vo);
	
	
	public List<MemberVO> searchListID(Map<String, String> map);
	public List<MemberVO> searchListNAME(Map<String, String> map);

	public List<MemberVO> selectAllPageBlock(Map<String, Integer> map);
	
	public List<MemberVO> searchListID_PAGE(Map<String, Object> map);
	public List<MemberVO> searchListNAME_PAGE(Map<String, Object> map);

	public int getTotalRows();

	public int search_total_rows_id(Map<String, String> map);

	public int search_total_rows_name(Map<String, String> map);
	
	MemberVO login(String memberId, String memberPw);
	
	 @Select("SELECT * FROM member WHERE memberId = #{memberId}")
	    MemberVO findMemberById(String memberId);
	 
	 @Update("UPDATE member SET sellerFollow = sellerFollow + 1 WHERE memberId = #{memberId}")
	    void increaseFollowerCount(@Param("memberId") String memberId);
	 
	 void addFollower(@Param("followerId") String followerId, @Param("followingId") String followingId);

	public List<MemberVO> findFollowingByMemberId(String memberId);
	

	 
	
}
