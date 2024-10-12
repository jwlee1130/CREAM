package com.cream.dao;

import java.sql.SQLException;

import com.cream.dto.PurchaseDTO;

public interface PurchaseDAO {
	/*
	 * 즉시 구매
	 */
	int nowBuy(PurchaseDTO purchase) throws SQLException;

	PurchaseDTO purchaseDetail(int userNo, int salesNo) throws SQLException;
}
