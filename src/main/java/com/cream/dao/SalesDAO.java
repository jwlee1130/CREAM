package com.cream.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.cream.dto.SalesDTO;

public interface SalesDAO {
	List<SalesDTO> selectAll(int shoesNo, int salesNo) throws SQLException;

	SalesDTO salesDetail(int salesNo) throws SQLException ;
	
	int closeSale(Connection con, int salesNo) throws SQLException;

	int insertSalesImg(int salesNo,String fileName,long fileSize);	
	
 
}
