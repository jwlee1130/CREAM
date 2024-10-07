package com.cream.dto;

public class ShoesSizeDTO {
    private int no;
    private int shoesSize;

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