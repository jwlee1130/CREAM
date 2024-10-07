package com.cream.dto;

public class SurveyDTO {
    private int userNo;
    private String category;
    private String brand;
    private String color;
    private int price;

    public SurveyDTO() {}

    public SurveyDTO(int userNo, String category, String brand, String color, int price) {
        this.userNo = userNo;
        this.category = category;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}