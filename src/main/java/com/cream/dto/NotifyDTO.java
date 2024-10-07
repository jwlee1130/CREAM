package com.cream.dto;

public class NotifyDTO {
    private int no;
    private int userNo;
    private int userSalesNo;
    private String msg;
    private int isRead;
    private String regdate;

    public NotifyDTO() {}

    public NotifyDTO(int no, int userNo, int userSalesNo, String msg, int isRead, String regdate) {
        this.no = no;
        this.userNo = userNo;
        this.userSalesNo = userSalesNo;
        this.msg = msg;
        this.isRead = isRead;
        this.regdate = regdate;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public int getUserSalesNo() { return userSalesNo; }
    public void setUserSalesNo(int userSalesNo) { this.userSalesNo = userSalesNo; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public int getIsRead() { return isRead; }
    public void setIsRead(int isRead) { this.isRead = isRead; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }
}