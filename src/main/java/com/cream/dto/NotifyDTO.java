package com.cream.dto;

import com.google.gson.annotations.Expose;

public class NotifyDTO {
	@Expose private int no;
	@Expose private int userNo;
	@Expose private int salesNo;
	@Expose  private int productNo;
	@Expose private String msg;
	@Expose private int isRead;
	@Expose private String regdate;

    public NotifyDTO() {}

    public NotifyDTO(int no, int userNo, int salesNo,int productNo, String msg, int isRead, String regdate) {
    	this(userNo,salesNo,productNo,msg);
        this.no = no;
        this.isRead = isRead;
        this.regdate = regdate;
    }
    public NotifyDTO(int userNo, int salesNo,int productNo, String msg) {
        this.userNo = userNo;
        this.salesNo = salesNo;
        this.productNo=productNo;
        this.msg = msg;
    }




    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    
    
    public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getSalesNo() { return salesNo; }
    public void setSalesNo(int salesNo) { this.salesNo = salesNo; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public int getIsRead() { return isRead; }
    public void setIsRead(int isRead) { this.isRead = isRead; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }
}