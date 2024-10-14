package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.PurchaseDTO;

public interface PurchaseService {
	int nowBuy(PurchaseDTO purchase) throws SQLException;

	PurchaseDTO purchaseDetail(int userNo, int salesNo) throws SQLException;
	
	List<PurchaseDTO> selectPurchase(int buyUserNo) throws SQLException;
}
