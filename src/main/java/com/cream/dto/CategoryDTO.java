package com.cream.dto;

import com.google.gson.annotations.Expose;

public class CategoryDTO {
	@Expose private int no;
	@Expose  private String category;

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