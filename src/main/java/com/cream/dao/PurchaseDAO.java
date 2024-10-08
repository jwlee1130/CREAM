package com.cream.dao;

import com.cream.dto.BidDTO;
import com.cream.dto.PurchaseDTO;

import java.sql.SQLException;

public interface PurchaseDAO {
    int nowBuy(PurchaseDTO purchase) throws SQLException; // 즉시 구매하기

    int bid(BidDTO bid) throws SQLException; // 입찰하기





}
