package com.cream.dto;

public class BidDTO {
    private int no;
    private int userNo;
    private int salesId;
    private int productPrice;
    private int regdate;

    public BidDTO() {}
    
    public BidDTO(int no, int userNo, int salesId, int productPrice, int regdate) {
        this.no = no;
        this.userNo = userNo;
        this.salesId = salesId;
        this.productPrice = productPrice;
        this.regdate = regdate;
    }
    
    
    public BidDTO(int no, int userNo, int salesId, int productPrice, int regdate) {
        this.no = no;
        this.userNo = userNo;
        this.salesId = salesId;
        this.productPrice = productPrice;
        this.regdate = regdate;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getSalesId() { return salesId; }
    public void setSalesId(int salesId) { this.salesId = salesId; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public int getRegdate() { return regdate; }
    public void setRegdate(int regdate) { this.regdate = regdate; }
}