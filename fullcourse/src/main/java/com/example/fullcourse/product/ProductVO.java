package com.example.fullcourse.product;

public class ProductVO {
	private int product_mnum;
	private String product_title;
	private int product_price;
	private String product_image;
	private int product_inventory;
	
	public ProductVO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ProductVO [product_mnum=" + product_mnum + ", product_title=" + product_title + ", product_price="
				+ product_price + ", product_image=" + product_image + ", product_inventory=" + product_inventory + "]";
	}


	public int getProduct_mnum() {
		return product_mnum;
	}


	public void setProduct_mnum(int product_mnum) {
		this.product_mnum = product_mnum;
	}


	public String getProduct_title() {
		return product_title;
	}


	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}


	public int getProduct_price() {
		return product_price;
	}


	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}


	public String getProduct_image() {
		return product_image;
	}


	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}


	public int getProduct_inventory() {
		return product_inventory;
	}


	public void setProduct_inventory(int product_inventory) {
		this.product_inventory = product_inventory;
	}


	public ProductVO(int product_mnum, String product_title, int product_price, String product_image,
			int product_inventory) {
		super();
		this.product_mnum = product_mnum;
		this.product_title = product_title;
		this.product_price = product_price;
		this.product_image = product_image;
		this.product_inventory = product_inventory;
	}
	
	
}