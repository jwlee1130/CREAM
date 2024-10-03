package com.cream.dao;

import java.sql.SQLException;

import com.cream.dto.UserDTO;

public interface UserDAO {
  /**
   * 로그인 기능
   * select user_id , pwd, name from users where user_id=? and pwd=?
   * */
	UserDTO loginCheck(UserDTO userDTO)throws SQLException;
}
