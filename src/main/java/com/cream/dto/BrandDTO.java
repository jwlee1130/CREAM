package com.cream.dto;

import com.google.gson.annotations.Expose;

public class BrandDTO {
	@Expose private int no;
	@Expose private String brand;

    public BrandDTO() {}

    public BrandDTO(int no, String brand) {
        this.no = no;
        this.brand = brand;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
}