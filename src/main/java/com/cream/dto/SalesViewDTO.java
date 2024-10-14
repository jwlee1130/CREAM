package com.cream.dto;

import com.google.gson.annotations.Expose;

public class SalesViewDTO {
    @Expose private int no;
    @Expose private int userNo;
    @Expose private int productNo;
    @Expose private String regdate;
    @Expose private int salesStatus;
    @Expose private int shoesSize;
    @Expose private String engName;
    @Expose private String filePath;

    public SalesViewDTO() {}

    public SalesViewDTO(int no, int userNo, int productNo, String regdate, int salesStatus, int shoesSize, String engName, String filePath) {
        this.no = no;
        this.userNo = userNo;
        this.productNo = productNo;
        this.regdate = regdate;
        this.salesStatus = salesStatus;
        this.shoesSize = shoesSize;
        this.engName = engName;
        this.filePath = filePath;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getProductNo() { return productNo; }
    public void setProductNo(int productNo) { this.productNo = productNo; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }

    public int getSalesStatus() { return salesStatus; }
    public void setSalesStatus(int salesStatus) { this.salesStatus = salesStatus; }

    public int getShoesSize() { return shoesSize; }
    public void setShoesSize(int shoesSize) { this.shoesSize = shoesSize; }

    public String getEngName() { return engName; }
    public void setEngName(String engName) { this.engName = engName; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}
