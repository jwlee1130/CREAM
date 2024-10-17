package com.cream.dao;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.PurchaseDTO;

public interface PurchaseDAO {
	/*
	 * 즉시 구매
	 */
	int nowBuy(PurchaseDTO purchase) throws SQLException;
	
	List<PurchaseDTO> selectPurchase(int buyUserNo) throws SQLException;

	public int calculateCommission(int userNo, int price) throws SQLException;

	PurchaseDTO purchaseBuyUserDetail(int buyUserNo, int salesNo) throws SQLException;

	PurchaseDTO purchaseSalesUserDetail(int buyUserNo, int salesNo) throws SQLException;
}
