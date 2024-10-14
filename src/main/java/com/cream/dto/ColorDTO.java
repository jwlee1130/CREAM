package com.cream.dto;

import com.google.gson.annotations.Expose;

public class ColorDTO {
	@Expose private int no;
	@Expose private String color;

    public ColorDTO() {}

    public ColorDTO(int no, String color) {
        this.no = no;
        this.color = color;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}