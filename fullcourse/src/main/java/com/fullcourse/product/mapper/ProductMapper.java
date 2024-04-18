package com.fullcourse.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.fullcourse.product.ProductVO;
import com.fullcourse.route.RouteVO;

@Mapper
public interface ProductMapper {
	
	
	public int insertOK(ProductVO productVO);
	
	public int updateOK(ProductVO productVO);
	
	public int deleteOK(ProductVO productVO);

	public List<RouteVO> findAllRoutes();
	
	public ProductVO selectProductById(int productNum);

}
