package com.cream.dto;

import com.google.gson.annotations.Expose;

public class ProductDTO {

	@Expose private int no;
    private int brandNo;
    private int categoryNo;
    private int shoesNo;
    private int colorNo;
    @Expose private String engName;
    private String korName;
    private String release;
    @Expose private int releasePrice;
    private String modelNumber;
    private String regdate;
    private int salesQuantity;
    private ProductImgDTO productImg;
    
    public ProductDTO(int no, int brandNo, int categoryNo, int shoesNo, int colorNo, String engName,
			String korName, String release, int releasePrice, String modelNumber, String regdate, int salesQuantity ) {
		this.no = no;
		this.brandNo = brandNo;
		this.categoryNo = categoryNo;
		this.shoesNo = shoesNo;
		this.colorNo = colorNo;
		this.engName = engName;
		this.korName = korName;
		this.release = release;
		this.releasePrice = releasePrice;
		this.modelNumber = modelNumber;
		this.regdate = regdate;
		this.salesQuantity = salesQuantity;
	}

    public ProductDTO(int no, int brandNo, int categoryNo, int shoesNo, int colorNo, String engName,
			String korName, String release, int releasePrice, String modelNumber, String regdate, int salesQuantity,
			ProductImgDTO productImg) {
		this.no = no;
		this.brandNo = brandNo;
		this.categoryNo = categoryNo;
		this.shoesNo = shoesNo;
		this.colorNo = colorNo;
		this.engName = engName;
		this.korName = korName;
		this.release = release;
		this.releasePrice = releasePrice;
		this.modelNumber = modelNumber;
		this.regdate = regdate;
		this.salesQuantity = salesQuantity;
		this.productImg = productImg;
	}





	public ProductDTO() {}




    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getBrandNo() { return brandNo; }
    public void setBrandNo(int brandNo) { this.brandNo = brandNo; }

    public int getCategoryNo() { return categoryNo; }
    public void setCategoryNo(int categoryNo) { this.categoryNo = categoryNo; }

    public int getShoesNo() { return shoesNo; }
    public void setShoesNo(int shoesNo) { this.shoesNo = shoesNo; }

    public int getColorNo() { return colorNo; }
    public void setColorNo(int colorNo) { this.colorNo = colorNo; }

    public String getEngName() { return engName; }
    public void setEngName(String engName) { this.engName = engName; }

    public String getKorName() { return korName; }
    public void setKorName(String korName) { this.korName = korName; }

    public String getRelease() { return release; }
    public void setRelease(String release) { this.release = release; }

    public int getReleasePrice() { return releasePrice; }
    public void setReleasePrice(int releasePrice) { this.releasePrice = releasePrice; }

    public String getModelNumber() { return modelNumber; }
    public void setModelNumber(String modelNumber) { this.modelNumber = modelNumber; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }

    public ProductImgDTO getProductImg() {
		return productImg;
	}

	public void setProductImg(ProductImgDTO productImg) {
		this.productImg = productImg;
	}

	public int getSalesQuantity() { return salesQuantity; }
    public void setSalesQuantity(int salesQuantity) { this.salesQuantity = salesQuantity; }
}