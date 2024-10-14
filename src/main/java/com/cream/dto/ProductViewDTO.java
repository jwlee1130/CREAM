package com.cream.dto;

import com.google.gson.annotations.Expose;

public class ProductViewDTO {
    @Expose private int no;
    @Expose private String engName;
    @Expose private String korName;
    @Expose private int releasePrice;
    @Expose private ProductImgDTO productImg;

    
    public ProductImgDTO getProductImg() {
		return productImg;
	}

	public void setProductImg(ProductImgDTO productImg) {
		this.productImg = productImg;
	}

	public ProductViewDTO(int no, String engName, String korName, int releasePrice) {
        this.no = no;
        this.engName = engName;
        this.korName = korName;
        this.releasePrice = releasePrice;
    }

    public ProductViewDTO() {
		
	}

	public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public String getEngName() { return engName; }
    public void setEngName(String engName) { this.engName = engName; }

    public String getKorName() { return korName; }
    public void setKorName(String korName) { this.korName = korName; }

    public int getReleasePrice() { return releasePrice; }
    public void setReleasePrice(int releasePrice) { this.releasePrice = releasePrice; }
}
