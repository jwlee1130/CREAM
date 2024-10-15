package com.cream.service;

import java.sql.SQLException;
import java.util.List;

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

	@Override
	public List<PurchaseDTO> selectPurchase(int buyUserNo) throws SQLException {
		List<PurchaseDTO> list=dao.selectPurchase(buyUserNo);
		return list;
	}

	@Override
	public int calculateCommission(int userNo, int price) throws SQLException {
		int commission = dao.calculateCommission(userNo, price);
		return commission;
	}

	
}
