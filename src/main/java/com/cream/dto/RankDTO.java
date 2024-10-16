package com.cream.dto;

import com.google.gson.annotations.Expose;

public class RankDTO {
	@Expose private int no;
	@Expose private String rank;
	@Expose private double discount;

    public RankDTO() {}

    public RankDTO(int no, String rank, double discount) {
        this.no = no;
        this.rank = rank;
        this.discount = discount;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public double getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
}