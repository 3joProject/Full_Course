package com.fullcourse.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullcourse.product.mapper.ProductMapper;


@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	public int insertOK(ProductVO productVO) {
		return productMapper.insertOK(productVO);
	}
	
	public int updateOK(ProductVO productVO) {
		return productMapper.updateOK(productVO);
		
	}
	public int deleteOK(ProductVO productVO) {
		return productMapper.deleteOK(productVO);
		
	}
	
	public List<ProductVO> selectAll() {
		
		return productMapper.selectAll();
	}
	
	public List<ProductVO> selectAllPageBlock(int cpage, int pageBlock) {
		
		int startRow = (cpage-1) * pageBlock + 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return productMapper.selectAllPageBlock(map);
	}
	
	public ProductVO selectOne(ProductVO productVO) {
		
		return productMapper.selectOne(productVO);
	}
	
	public List<ProductVO> searchList(String searchKey, String searchWord) {
		
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("productMid")) {
			return productMapper.searchListMid(map);
		}else {
			return productMapper.searchListTitle(map);
		}
	}
	
	public int getTotalRows() {
        return productMapper.getTotalRows();
    }
	
	public int getSearchTotalRows(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("productMid")) {
			return productMapper.search_total_rows_productMid(map);
		}else {
			return productMapper.search_total_rows_productTitle(map);
		}
	}

	public List<ProductVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {

		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		if(searchKey.equals("id")) {
			return productMapper.searchListMid_PAGE(map);
		}else {
			return productMapper.searchListTitle_PAGE(map);
		}
	}
	
	
}
