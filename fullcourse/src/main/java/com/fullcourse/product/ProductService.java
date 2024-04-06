package com.fullcourse.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullcourse.product.mapper.ProductMapper;
@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	public void insert(ProductVO productVO) {
		productMapper.insert(productVO);
	}
	
	public void update(ProductVO productVO) {
		productMapper.update(productVO);
		
	}
	public void delete(int productNum) {
		productMapper.delete(productNum);
		
	}
	
	public List<ProductVO> selectAll() {
		
		return productMapper.selectAll();
	}
	public ProductVO selectOne(int productNum) {
		
		return productMapper.selectOne(productNum);
	}
	
	public List<ProductVO> searchList(String searchType, String keyword) {

		return productMapper.searchList(searchType, keyword);
	}
	
	public int getTotalRows() {
        return productMapper.getTotalRows();
    }
	
	
}
