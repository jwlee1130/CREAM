package com.cream.dto;

import com.google.gson.annotations.Expose;

public class ProductDTO {

	@Expose private int no;         //상품번호 PK
	@Expose private int brandNo;    //브랜드번호 FK
	@Expose private int categoryNo; //카테고리번호 FK
	@Expose private int colorNo;    //색깔번호 FK
    @Expose private String engName;
    @Expose private String korName;
    @Expose private String release;
    @Expose private int releasePrice;
    @Expose private String modelNumber;
    @Expose private String regdate;
    @Expose private int salesQuantity;
    @Expose private ProductImgDTO productImg;
    @Expose private BrandDTO brandName;
    
	public ProductDTO() {}
	
	public ProductDTO(String modelNumber,String engName,
				String korName) {
		
			this.engName = engName;
			this.korName = korName;
			this.modelNumber = modelNumber;		
	}
	 
	public ProductDTO(String modelNumber,String engName,
				String korName, ProductImgDTO productImg) {

			this(modelNumber, engName, korName);
			this.productImg = productImg;
			
	}
	
    public ProductDTO(int no, int brandNo, int categoryNo, int shoesNo, String engName,
			String korName, String release, int releasePrice, String modelNumber, String regdate, int salesQuantity ) {
    	
		this(modelNumber, engName, korName);
    	this.no = no;
		this.brandNo = brandNo;
		this.categoryNo = categoryNo;
		this.colorNo = colorNo;
		this.release = release;
		this.releasePrice = releasePrice;
		this.regdate = regdate;
		this.salesQuantity = salesQuantity;
	}

   
    public ProductDTO(int no, int brandNo, int categoryNo, int colorNo, String engName,
			String korName, String release, int releasePrice, String modelNumber, String regdate, int salesQuantity,
			ProductImgDTO productImg) {
    	
    	this(no, brandNo, categoryNo, colorNo, engName, korName, release, releasePrice, modelNumber, regdate, salesQuantity);
		this.productImg = productImg;
	}

    public ProductDTO(int no, int brandNo, int categoryNo, int colorNo, String engName, String korName,
			String release, int releasePrice, String modelNumber, String regdate, int salesQuantity, ProductImgDTO productImg, BrandDTO brandName) { //인수 14개
    	
		this(no, brandNo, categoryNo, colorNo, engName, korName, release, releasePrice, modelNumber, regdate, salesQuantity, productImg);
		this.brandName = brandName;
	}

	public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getBrandNo() { return brandNo; }
    public void setBrandNo(int brandNo) { this.brandNo = brandNo; }

    public int getCategoryNo() { return categoryNo; }
    public void setCategoryNo(int categoryNo) { this.categoryNo = categoryNo; }

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

    public ProductImgDTO getProductImg() { return productImg;	}
	public void setProductImg(ProductImgDTO productImg) { this.productImg = productImg;	}

	public int getSalesQuantity() { return salesQuantity; }
    public void setSalesQuantity(int salesQuantity) { this.salesQuantity = salesQuantity; }
    
    public BrandDTO getBrandName() { return brandName;	}
    public void setBrandName(BrandDTO brandName) { this.brandName = brandName;	}
}