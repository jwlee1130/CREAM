package com.cream.dto;

public class BrandDTO {
    private int no;
    private String brand;

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