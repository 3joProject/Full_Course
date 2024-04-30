package com.fullcourse.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
	private int productNum;
	private String productMid;
	private String productTitle;
	private int productPrice;
	private String productImage;
	private String productContent;
	private int productInventory;
	private int productGuideNum;
	private MultipartFile file;
	
}