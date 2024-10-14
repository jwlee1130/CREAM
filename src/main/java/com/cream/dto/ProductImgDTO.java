package com.cream.dto;

public class ProductImgDTO {
    private int no;
    private int productNo;
    private String filePath;
    private String fileSize;
    private String regdate;

    public ProductImgDTO() {}

    public ProductImgDTO(String filePath, String fileSize) {
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
    
    public ProductImgDTO(int no, int productNo, String filePath, String fileSize, String regdate) {
        this.no = no;
        this.productNo = productNo;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.regdate = regdate;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getProductId() { return productNo; }
    public void setProductId(int productNo) { this.productNo = productNo; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }
}