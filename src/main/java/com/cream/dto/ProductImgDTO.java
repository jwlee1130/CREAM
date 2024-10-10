package com.cream.dto;

public class ProductImgDTO {
    private int no;
    private int productNo;
    private int no2;
    private String filePath;
    private String fileSize;
    private String regdate;

    public ProductImgDTO() {}

    public ProductImgDTO(int no, int productNo, int no2, String filePath, String fileSize, String regdate) {
        this.no = no;
        this.productNo = productNo;
        this.no2 = no2;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.regdate = regdate;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getProductId() { return productNo; }
    public void setProductId(int productNo) { this.productNo = productNo; }

    public int getNo2() { return no2; }
    public void setNo2(int no2) { this.no2 = no2; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }
}