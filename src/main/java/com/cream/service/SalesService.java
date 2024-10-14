package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.SalesDTO;

public interface SalesService {
	List<SalesDTO> selectAll(int shoesNo, int salesNo) throws SQLException;

	SalesDTO salesDetail(int salesNo)  throws SQLException;

	int insertSalesImg(int salesNo, String fileName, long fileSize);
}
