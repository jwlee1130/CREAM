package com.cream.dto;

public class PurchaseDTO {
    private int no;
    private int salesNo;
    private int userNo;
    private int productNo;
    private int price;
    private String regdate;
    private String address;

    public PurchaseDTO() {}

    public PurchaseDTO(int no, int salesNo, int userNo, int productNo, int price, String regdate, String address) {
        this.no = no;
        this.salesNo = salesNo;
        this.userNo = userNo;
        this.productNo = productNo;
        this.price = price;
        this.regdate = regdate;
        this.address = address;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getSalesNo() { return salesNo; }
    public void setSalesNo(int salesNo) { this.salesNo = salesNo; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getProductNo() { return productNo; }
    public void setProductNo(int productNo) { this.productNo = productNo; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}