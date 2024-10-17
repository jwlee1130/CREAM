package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dao.SalesDAO;
import com.cream.dao.SalesDAOImpl;
import com.cream.dto.SalesDTO;

public class SalesServiceImpl implements SalesService {
	SalesDAO dao = new SalesDAOImpl();
	
	@Override
	public List<SalesDTO> selectAll(int shoesNo, int productNo) throws SQLException {
		List<SalesDTO> list = dao.selectAll(shoesNo,productNo);
		
		return list;
	}

	@Override
	public SalesDTO salesDetail(int salesNo) throws SQLException {
		SalesDTO sale = dao.salesDetail(salesNo);
		if(sale==null) {
			throw new SQLException("입찰이 종료되었씁니다");
		}
		return sale;
	}

	@Override
	public int insertSalesImg(int salesNo, String fileName, long fileSize) {
		int result = dao.insertSalesImg(salesNo,fileName,fileSize);
		return result;
	}
	
	
}
