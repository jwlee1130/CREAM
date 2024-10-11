package com.cream.dto;

public class BidAccountDTO {

    private int no;        // 입찰금 관리 계좌 번호
    private int bidNo;     // 입찰 번호
    private int userNo;    // 사용자 번호
    private int salesId;   // 판매 ID
    private int price;     // 입찰 금액
    private int flag;
    // 기본 생성자
    public BidAccountDTO() {}

    // 모든 필드를 받는 생성자
    public BidAccountDTO(int no, int bidNo, int userNo, int salesId, int price,int flag) {
        this.no = no;
        this.bidNo = bidNo;
        this.userNo = userNo;
        this.salesId = salesId;
        this.price = price;
        this.flag = flag;
    }

    // Getter 및 Setter 메소드
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getBidNo() {
        return bidNo;
    }

    public void setBidNo(int bidNo) {
        this.bidNo = bidNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // toString 메소드 (객체 정보를 문자열로 표현)
    @Override
    public String toString() {
        return "BidAccountDTO{" +
                "no=" + no +
                ", bidNo=" + bidNo +
                ", userNo=" + userNo +
                ", salesId=" + salesId +
                ", price=" + price +
                '}';
    }
}
