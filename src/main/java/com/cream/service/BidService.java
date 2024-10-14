package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.BidAccountDTO;

public interface BidService {

	List<BidAccountDTO> getActiveBids(int salesNo) throws SQLException;

}
