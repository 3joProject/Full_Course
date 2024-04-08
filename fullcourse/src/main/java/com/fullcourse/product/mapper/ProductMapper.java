package com.fullcourse.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.fullcourse.product.ProductVO;

@Mapper
public interface ProductMapper {
	public int insertOK(ProductVO productVO);
	
	public int updateOK(ProductVO productVO);
	
	public int deleteOK(ProductVO productVO);
	
	public List<ProductVO> selectAll();
	
	public ProductVO selectOne(ProductVO productVO);
	
	List<ProductVO> searchList(String searchKey, String searchWord);
	
	public List<ProductVO> searchListMid(Map<String, String> map);
	public List<ProductVO> searchListTitle(Map<String, String> map);

	public List<ProductVO> selectAllPageBlock(Map<String, Integer> map);
	
	public List<ProductVO> searchListMid_PAGE(Map<String, Object> map);
	public List<ProductVO> searchListTitle_PAGE(Map<String, Object> map);

	public int getTotalRows();

	public int search_total_rows_productMid(Map<String, String> map);

	public int search_total_rows_productTitle(Map<String, String> map);
}
