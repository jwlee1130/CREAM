package com.cream.dto;

import com.google.gson.annotations.Expose;

public class WishListDTO {
    @Expose int no;
    @Expose private int productNo;
    @Expose private int userNo;

    public WishListDTO() {}

    public WishListDTO(int no, int productNo, int userNo) {
        this.no = no;
        this.productNo = productNo;
        this.userNo = userNo;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getProductNo() { return productNo; }
    public void setProductNo(int productNo) { this.productNo = productNo; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }
}