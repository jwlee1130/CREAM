package com.cream.dto;

import com.google.gson.annotations.Expose;

public class SalesDTO {
	@Expose private int no;
	@Expose private int userNo;
	@Expose private int productNo;
	@Expose private int startingPrice;
	@Expose private int nowPrice;
	@Expose private int salesStatus;
	@Expose private String regdate;
	@Expose private char grade;
	@Expose private int shoesNo;
    @Expose private ProductDTO product;
    @Expose private BidAccountDTO bidAccount;
    @Expose private ShoesSizeDTO shoesSize;
    @Expose private SalesImgDTO salesImg;
    
    public ShoesSizeDTO getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(ShoesSizeDTO shoesSize) {
		this.shoesSize = shoesSize;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public SalesDTO() {}

    public SalesDTO(int no, int userNo, int productNo, int startingPrice, int nowPrice, int salesStatus, String regdate, char grade,int shoesNo) {
        this.no = no;
        this.userNo = userNo;
        this.productNo = productNo;
        this.startingPrice = startingPrice;
        this.nowPrice = nowPrice;
        this.salesStatus = salesStatus;
        this.regdate = regdate;
        this.grade = grade;
        this.shoesNo = shoesNo;
    }
    
    

    public BidAccountDTO getBidAccount() {
		return bidAccount;
	}

	public void setBidAccount(BidAccountDTO bidAccount) {
		this.bidAccount = bidAccount;
	}

	public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getProductNo() { return productNo; }
    public void setProductNo(int productNo) { this.productNo = productNo; }

    public int getStartingPrice() { return startingPrice; }
    public void setStartingPrice(int startingPrice) { this.startingPrice = startingPrice; }

    public int getNowPrice() { return nowPrice; }
    public void setNowPrice(int nowPrice) { this.nowPrice = nowPrice; }

    public int getSalesStatus() { return salesStatus; }
    public void setSalesStatus(int salesStatus) { this.salesStatus = salesStatus; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }

    public char getGrade() { return grade; }
    public void setGrade(char grade) { this.grade = grade; }

	public int getShoesNo() {
		return shoesNo;
	}

	public void setShoesNo(int shoesNo) {
		this.shoesNo = shoesNo;
	}

	public SalesImgDTO getSalesImg() {
		return salesImg;
	}

	public void setSalesImg(SalesImgDTO salesImg) {
		this.salesImg = salesImg;
	}
	
    
}