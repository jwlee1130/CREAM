package com.cream.dao;

import com.cream.dto.BidDTO;
import com.cream.dto.PurchaseDTO;

import java.sql.SQLException;

public interface PurchaseDAO {
    int nowBuy(PurchaseDTO purchase) throws SQLException; // 즉시 구매하기
    
    /**
     * 	슈크림 사용하기
     * */
    int useCream(int user_no, int price) throws SQLException;
    
    



}
