package com.cream.dto;

public class BidDTO {
    private int no;
    private int userNo;
    private int salesNO;
    private int productPrice;
    private int regdate;
    
    
    public BidDTO() {}
    
    public BidDTO(int no, int userNo, int salesNO, int productPrice, int regdate) {
        this(userNo,salesNO,productPrice);
    	this.no = no;
        this.regdate = regdate;
    }
    
    
    public BidDTO(int userNo, int salesNO, int productPrice) {
        this.userNo = userNo;
        this.salesNO = salesNO;
        this.productPrice = productPrice;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getSalesNO() { return salesNO; }
    public void setSalesNO(int userNo) { this.salesNO = userNo; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public int getRegdate() { return regdate; }
    public void setRegdate(int regdate) { this.regdate = regdate; }
}