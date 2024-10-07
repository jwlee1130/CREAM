package com.cream.dto;

public class CategoryDTO {
    private int no;
    private String category;

    public CategoryDTO() {}

    public CategoryDTO(int no, String category) {
        this.no = no;
        this.category = category;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}