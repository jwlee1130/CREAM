package com.cream.dto;

import com.google.gson.annotations.Expose;

public class ShoesSizeDTO {
    @Expose int no;
    @Expose private int shoesSize;

    public ShoesSizeDTO() {}

    public ShoesSizeDTO(int no, int shoesSize) {
        this.no = no;
        this.shoesSize = shoesSize;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getShoesSize() { return shoesSize; }
    public void setShoesSize(int shoesSize) { this.shoesSize = shoesSize; }
}