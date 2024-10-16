package com.cream.dto;

import com.google.gson.annotations.Expose;

public class RankDTO {
	@Expose private int no;
	@Expose private String rank;
	@Expose private int discount;

    public RankDTO() {}

    public RankDTO(int no, String rank, int discount) {
        this.no = no;
        this.rank = rank;
        this.discount = discount;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
}