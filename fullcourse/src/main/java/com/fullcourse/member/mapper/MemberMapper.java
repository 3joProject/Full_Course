package com.fullcourse.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.member.MemberVO;

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
}
