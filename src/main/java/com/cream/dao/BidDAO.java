package com.cream.dao;

import com.cream.dto.BidDTO;

import java.sql.SQLException;

public interface BidDAO {
    BidDTO getHighestBid(int sales_no) throws SQLException; // 최고 입찰 확인

    String getTimeBid(int sales_no) throws SQLException; // 남은 시간 표현


}
