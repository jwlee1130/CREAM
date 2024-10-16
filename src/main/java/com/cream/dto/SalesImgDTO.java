package com.cream.dto;

import com.google.gson.annotations.Expose;

public class SalesImgDTO {
	@Expose int no;
	@Expose int salesNo;
	@Expose String filePath;
	
	public SalesImgDTO(int no, int salesNo, String filePath) {
		this(salesNo,filePath);
		this.no = no;
		
	}
	
	public SalesImgDTO(int salesNo, String filePath) {
		this.salesNo = salesNo;
		this.filePath = filePath;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(int salesNo) {
		this.salesNo = salesNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
}
