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
	


}
