package com.cream.service;

import java.sql.SQLException;

import com.cream.dao.PurchaseDAO;
import com.cream.dao.PurchaseDAOImpl;
import com.cream.dto.PurchaseDTO;

public class PurchaseServiceImpl implements PurchaseService {
	PurchaseDAO dao = new PurchaseDAOImpl();
	
	@Override
	public int nowBuy(PurchaseDTO purchase) throws SQLException {
		int result = dao.nowBuy(purchase);
			
		return result;
	}

	@Override
	public PurchaseDTO purchaseDetail(int userNo, int salesNo) throws SQLException{
		PurchaseDTO purchase = dao.purchaseDetail(userNo,salesNo);
		return purchase;
	}

}
