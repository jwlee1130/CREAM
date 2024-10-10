package com.cream.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.cream.dto.BidDTO;

public interface BidDAO {
    BidDTO getHighestBid(BidDTO bid) throws SQLException; // 최고 입찰 확인

    String getTimeBid(int sales_no) throws SQLException; // 남은 시간 표현
    
    /**
     * 	입찰하기
     * */
    int bid(BidDTO bid) throws SQLException;
    
    /**
     * 	입찰금액 입금
     * */
    int checkBalance(Connection con, int user_no, int price) throws SQLException;
    
    /**
     * 	입찰금액 돌려주기
     * */
    int refundBidAmount(int sales_no,int user_no) throws SQLException;
    

}
