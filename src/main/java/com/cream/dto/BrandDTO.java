package com.cream.dto;

import com.google.gson.annotations.Expose;

public class BrandDTO {
	@Expose private int no;
	@Expose private String brand;

    public BrandDTO() {}
    
    public BrandDTO(String brand) { //전체 상품 조회 시 필요한 생성자
    	this.brand = brand;
    }

    public BrandDTO(int no, String brand) {
        this(brand);
    	this.no = no;
        
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
}