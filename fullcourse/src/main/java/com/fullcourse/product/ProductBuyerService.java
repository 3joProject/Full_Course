package com.fullcourse.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcourse.product.mapper.ProductBuyerMapper;


@Service
public class ProductBuyerService {

	@Autowired
	private ProductBuyerMapper mapper ;
	
	public List<ProductVO> selectAll() {
		
		return mapper.selectAll();
	}
	
	public List<ProductVO> selectAllPageBlock(int cpage, int pageBlock) {
		
		int startRow = (cpage-1) * pageBlock + 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		return mapper.selectAllPageBlock(map);
	}
	
	public ProductVO selectOne(ProductVO vo) {
		
		return mapper.selectOne(vo);
	}
	
	public List<ProductVO> searchList(String searchKey, String searchWord) {
		
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("productMid")) {
			return mapper.searchListMid(map);
		}else {
			return mapper.searchListTitle(map);
		}
	}
	
	public int getTotalRows() {
        return mapper.getTotalRows();
    }
	
	public int getSearchTotalRows(String searchKey, String searchWord) {
		Map<String, String> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		if(searchKey.equals("productMid")) {
			return mapper.search_total_rows_productMid(map);
		}else {
			return mapper.search_total_rows_productTitle(map);
		}
	}

	public List<ProductVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {

		Map<String, Object> map = new HashMap<>();
		map.put("searchWord", "%"+searchWord+"%");
		
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		if(searchKey.equals("productMid")) {
			return mapper.searchListMid_PAGE(map);
		}else {
			return mapper.searchListTitle_PAGE(map);
		}
	}

	public List<ProductVO> selectAllOrderBy(int cpage, int pageBlock, String orderBy) {
		Map<String, Object> map = new HashMap<>();
		map.put("orderBy", orderBy);
		
		int startRow = (cpage - 1) * pageBlock + 1;
		map.put("startRow", startRow-1);
		map.put("pageBlock", pageBlock);
		
		
		return mapper.selectAllOrderBy(map);
		
	}
	
	
	
}
