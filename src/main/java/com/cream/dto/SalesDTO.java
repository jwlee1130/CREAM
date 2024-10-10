package com.cream.dto;

public class SalesDTO {
    private int no;
    private int userNo;
    private int productNo;
    private int shoesNo;
    private int no2;
    private int startingPrice;
    private int nowPrice;
    private int salesStatus;
    private String regdate;
    private char grade;

    public SalesDTO() {}

    public SalesDTO(int no, int userNo, int productNo, int shoesNo, int no2, int startingPrice, int nowPrice, int salesStatus, String regdate, char grade) {
        this.no = no;
        this.userNo = userNo;
        this.productNo = productNo;
        this.shoesNo = shoesNo;
        this.no2 = no2;
        this.startingPrice = startingPrice;
        this.nowPrice = nowPrice;
        this.salesStatus = salesStatus;
        this.regdate = regdate;
        this.grade = grade;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getProductNo() { return productNo; }
    public void setProductNo(int productNo) { this.productNo = productNo; }

    public int getShoesNo() { return shoesNo; }
    public void setShoesNo(int shoesNo) { this.shoesNo = shoesNo; }

    public int getNo2() { return no2; }
    public void setNo2(int no2) { this.no2 = no2; }

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
}