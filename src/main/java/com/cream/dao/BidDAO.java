package com.cream.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.cream.dto.BidAccountDTO;
import com.cream.dto.BidDTO;

public interface BidDAO {
	
    void bidTransaction(BidDTO bid,int productNO) throws SQLException;

    String getTimeBid(int sales_no) throws SQLException; // 남은 시간 표현
    
    /**
     * 	입찰하기
     * */
    int bid(Connection con, BidDTO bid) throws SQLException;
    
    /**
     * 	입찰금액 입금
     * */
    int checkBalance(Connection con, int user_no, int price) throws SQLException;
    
    /**
     * 	입찰금액 돌려주기
     * */
    int refundBidAmount(Connection con, int sales_no,int user_no) throws SQLException;
    /*
     * 관리계좌의 입찰정보 가져오기
     */
    BidAccountDTO getHighestBid(Connection con, int salesNo) throws SQLException;
    
    /*
     * 플래그 1로 설정해서 입찰완료될떄까지 접근 x
     */
    int setBidFlagOne(Connection con, int salesNO) throws SQLException;

    /*
     * 플래그 1로 설정해서 입찰완료될떄까지 접근 x
     */
    int setBidFlagZero(Connection con, int salesNO) throws SQLException;


}
