package com.fullcourse.product;

public class ProductVO {
	private int productNum;
	private int productMnum;
	private String productTitle;
	private int productPrice;
	private String productImage;
	private int productInventory;
	
	public ProductVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductVO(int productNum, int productMnum, String productTitle, int productPrice, String productImage,
			int productInventory) {
		super();
		this.productNum = productNum;
		this.productMnum = productMnum;
		this.productTitle = productTitle;
		this.productPrice = productPrice;
		this.productImage = productImage;
		this.productInventory = productInventory;
	}

	@Override
	public String toString() {
		return "ProductVO [productNum=" + productNum + ", productMnum=" + productMnum + ", productTitle=" + productTitle
				+ ", productPrice=" + productPrice + ", productImage=" + productImage + ", productInventory="
				+ productInventory + "]";
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getProductMnum() {
		return productMnum;
	}

	public void setProductMnum(int productMnum) {
		this.productMnum = productMnum;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}
	
	
}