package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.PurchaseDTO;

public interface PurchaseService {
	int nowBuy(PurchaseDTO purchase) throws SQLException;
	
	List<PurchaseDTO> selectPurchase(int buyUserNo) throws SQLException;
	
	int calculateCommission(int userNo,int price) throws SQLException;

	PurchaseDTO purchaseBuyUserDetail(int userNo, int salesNo) throws SQLException;

	PurchaseDTO purchaseSalesUserDetail(int userNo, int salesNo) throws SQLException;
}
