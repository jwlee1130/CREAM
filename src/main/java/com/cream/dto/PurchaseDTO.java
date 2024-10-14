package com.cream.dto;

import com.google.gson.annotations.Expose;

public class PurchaseDTO {
	@Expose  private int no;
	@Expose private int salesNo;
	@Expose private int salesUserNo;
	@Expose  private int buyUserNo;
	@Expose private int productNo;
	@Expose  private int price;
	@Expose private String regdate;
	@Expose private String address;

    public PurchaseDTO() {}

    public PurchaseDTO(int no, int salesNo, int salesUserNo, int buyUserNo, int productNo, int price, String regdate, String address) {
        this(salesNo, salesUserNo, buyUserNo, productNo, price, address);
    	this.no = no;
        this.regdate = regdate;
    
    }

    public PurchaseDTO(int salesNo, int salesUserNo, int buyUserNo, int productNo, int price, String address) {
        this.salesNo = salesNo;
        this.salesUserNo = salesUserNo;
        this.buyUserNo = buyUserNo;
        this.productNo = productNo;
        this.price = price;
        this.address = address;
	}

	public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getSalesNo() { return salesNo; }
    public void setSalesNo(int salesNo) { this.salesNo = salesNo; }

    public int getSalesUserNo() {
		return salesUserNo;
	}

	public void setSalesUserNo(int salesUserNo) {
		this.salesUserNo = salesUserNo;
	}

	public int getBuyUserNo() {
		return buyUserNo;
	}

	public void setBuyUserNo(int buyUserNo) {
		this.buyUserNo = buyUserNo;
	}

	public int getProductNo() { return productNo; }
    public void setProductNo(int productNo) { this.productNo = productNo; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}