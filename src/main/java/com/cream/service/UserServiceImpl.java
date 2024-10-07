package com.cream.service;

import java.sql.SQLException;

import com.cream.dao.UserDAOImpl;
import com.cream.dto.BidDTO;
import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;

public class UserServiceImpl implements UserService {
	UserDAOImpl dao = new UserDAOImpl();

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException, AuthenticationException {
		UserDTO checkUser = dao.loginCheck(userDTO);
		if(checkUser==null) {//실패
			throw new SQLException();
		}
		return checkUser;
		
	}

	@Override
	public BidDTO findBidByUserNo(int no) throws SQLException, AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
