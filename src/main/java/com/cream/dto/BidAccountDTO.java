package com.cream.dto;

import com.google.gson.annotations.Expose;

public class BidAccountDTO {

	@Expose private int salesNo;       // SALES_NO
	@Expose  private int buyUserNo;     // BUY_USER_NO
	@Expose   private int price;         // PRICE
	
	@Expose private String engName;
    @Expose private String regdate;
    @Expose private int shoesSize;
    @Expose private String filePath;
	    
	    public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getShoesSize() {
		return shoesSize;
	}
	public void setShoesSize(int shoesSize) {
		this.shoesSize = shoesSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
		public BidAccountDTO(int salesNo, int buyUserNo, int price, String engName, String regdate, int shoesSize,
			String filePath) {
		super();
		this.salesNo = salesNo;
		this.buyUserNo = buyUserNo;
		this.price = price;
		this.engName = engName;
		this.regdate = regdate;
		this.shoesSize = shoesSize;
		this.filePath = filePath;
	}
		public BidAccountDTO() {}
	    // 기본 생성자
	    public BidAccountDTO(int buyUserNo, int price) {
	    	this(price);
	    	this.buyUserNo = buyUserNo;
	    }

	    // 매개변수를 받는 생성자
	    public BidAccountDTO(int salesNo, int buyUserNo, int price) {
	    	this(buyUserNo,price);
	    	this.salesNo = salesNo;
	    }

	    public BidAccountDTO(int price) {
	    	this.price = price;
	    }

		// Getter와 Setter 메서드
	    public int getSalesNo() {
	        return salesNo;
	    }

	    public void setSalesNo(int salesNo) {
	        this.salesNo = salesNo;
	    }

	    public int getBuyUserNo() {
	        return buyUserNo;
	    }

	    public void setBuyUserNo(int buyUserNo) {
	        this.buyUserNo = buyUserNo;
	    }

	    public int getPrice() {
	        return price;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }
}
