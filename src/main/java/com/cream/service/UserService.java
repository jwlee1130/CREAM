 package com.cream.service;

import java.sql.SQLException;

import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;

public interface UserService {
	/**
	 * 로그인 체크
	 * */
   UserDTO loginCheck(UserDTO userDTO)throws SQLException , AuthenticationException;
}
