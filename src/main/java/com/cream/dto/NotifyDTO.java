package com.cream.dto;

public class NotifyDTO {
    private int no;
    private int userNo;
    private int salesNo;
    private String msg;
    private int isRead;
    private String regdate;

    public NotifyDTO() {}

    public NotifyDTO(int no, int userNo, int salesNo, String msg, int isRead, String regdate) {
    	this(userNo,salesNo,msg);
        this.no = no;
        this.isRead = isRead;
        this.regdate = regdate;
    }
    
    public NotifyDTO(int userNo, int salesNo, String msg) {
        this.userNo = userNo;
        this.salesNo = salesNo;
        this.msg = msg;
    }


    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getSalesNo() { return salesNo; }
    public void setSalesNo(int salesNo) { this.salesNo = salesNo; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public int getIsRead() { return isRead; }
    public void setIsRead(int isRead) { this.isRead = isRead; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }
}