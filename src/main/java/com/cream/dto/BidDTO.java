package com.cream.dto;

import com.google.gson.annotations.Expose;

public class BidDTO {
	@Expose private int no;            // NO
	@Expose private int buyUserNo;     // BUY_USER_NO
	@Expose  private int salesNo;       // SALES_NO
	@Expose private int price;         // PRICE
	@Expose private int regdate;       // REGDATE

    public BidDTO() {}
    
    public BidDTO(int buyUserNo, int salesNO, int price) {
        this.buyUserNo = buyUserNo;
        this.salesNo = salesNO;
        this.price = price;
    }
    


    // 매개변수를 받는 생성자
    public BidDTO(int no, int buyUserNo, int salesNo, int price, int regdate) {
        this(buyUserNo,salesNo,price);
    	this.no = no;
        this.regdate = regdate;
    }

    // Getter와 Setter 메서드
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getBuyUserNo() {
        return buyUserNo;
    }

    public void setBuyUserNo(int buyUserNo) {
        this.buyUserNo = buyUserNo;
    }

    public int getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(int salesNo) {
        this.salesNo = salesNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRegdate() {
        return regdate;
    }

    public void setRegDate(int regdate) {
        this.regdate = regdate;
    }

}