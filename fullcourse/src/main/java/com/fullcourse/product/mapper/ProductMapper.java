package com.fullcourse.product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.fullcourse.product.ProductVO;
@Mapper
public interface ProductMapper {
	void insert(ProductVO productVO);
	void update(ProductVO productVO);
	void delete(int productNum);
	List<ProductVO> seletctAll();
	ProductVO selectOne(int productNum);
	List<ProductVO> searchList(String searchType, String keyword);
	int getTotalRows();
}
