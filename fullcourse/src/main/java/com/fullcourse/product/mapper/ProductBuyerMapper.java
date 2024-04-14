package com.fullcourse.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fullcourse.product.ProductVO;
import com.fullcourse.product.productReview.ProductReviewVO;

@Mapper
public interface ProductBuyerMapper {

	public List<ProductVO> selectAll();
	
	public ProductVO selectOne(ProductVO vo);
	
	List<ProductVO> searchList(String searchKey, String searchWord);
	
	public List<ProductVO> searchListMid(Map<String, String> map);
	public List<ProductVO> searchListTitle(Map<String, String> map);

	public List<ProductVO> selectAllPageBlock(Map<String, Integer> map);
	
	public List<ProductVO> searchListMid_PAGE(Map<String, Object> map);
	public List<ProductVO> searchListTitle_PAGE(Map<String, Object> map);

	public int getTotalRows();

	public int search_total_rows_productMid(Map<String, String> map);

	public int search_total_rows_productTitle(Map<String, String> map);

	public List<ProductVO> selectAllOrderBy(Map<String, Object> map);

	public List<ProductReviewVO> productReview(ProductVO vo);
	
}
