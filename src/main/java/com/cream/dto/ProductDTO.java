package com.cream.dto;

public class ProductDTO {
    private int id;
    private int brandNo;
    private int categoryNo;
    private int shoesNo;
    private int colorNo;
    private String categoty;
    private String engName;
    private String korName;
    private String release;
    private int releasePrice;
    private String modelNumber;
    private String regdate;
    private int salesQuantity;
    
    private ProductImgDTO productImgDTO;

    public ProductDTO() {}

    public ProductDTO(int id, int brandNo, int categoryNo, int shoesNo, int colorNo, String categoty, String engName, String korName, String release, int releasePrice, String modelNumber, String regdate, int salesQuantity) {
        this.id = id;
        this.brandNo = brandNo;
        this.categoryNo = categoryNo;
        this.shoesNo = shoesNo;
        this.colorNo = colorNo;
        this.categoty = categoty;
        this.engName = engName;
        this.korName = korName;
        this.release = release;
        this.releasePrice = releasePrice;
        this.modelNumber = modelNumber;
        this.regdate = regdate;
        this.salesQuantity = salesQuantity;
    }
    public ProductImgDTO getProductImgDTO() {
        return productImgDTO;
    }

    public void setProductImgDTO(ProductImgDTO productImgDTO) {
        this.productImgDTO = productImgDTO;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBrandNo() { return brandNo; }
    public void setBrandNo(int brandNo) { this.brandNo = brandNo; }

    public int getCategoryNo() { return categoryNo; }
    public void setCategoryNo(int categoryNo) { this.categoryNo = categoryNo; }

    public int getShoesNo() { return shoesNo; }
    public void setShoesNo(int shoesNo) { this.shoesNo = shoesNo; }

    public int getColorNo() { return colorNo; }
    public void setColorNo(int colorNo) { this.colorNo = colorNo; }

    public String getCategoty() { return categoty; }
    public void setCategoty(String categoty) { this.categoty = categoty; }

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

    public int getSalesQuantity() { return salesQuantity; }
    public void setSalesQuantity(int salesQuantity) { this.salesQuantity = salesQuantity; }
}