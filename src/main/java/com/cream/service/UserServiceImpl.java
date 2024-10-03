package com.cream.service;

import java.sql.SQLException;

import com.cream.dao.UserDAOImpl;
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
}
