package com.cream.service;

import java.sql.SQLException;

import com.cream.dto.PurchaseDTO;

public interface PurchaseService {
	int nowBuy(PurchaseDTO purchase) throws SQLException;

	PurchaseDTO purchaseDetail(int userNo, int salesNo) throws SQLException;

}
