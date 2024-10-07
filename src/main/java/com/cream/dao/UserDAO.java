package com.cream.dao;

import java.sql.SQLException;

import com.cream.dto.BidDTO;
import com.cream.dto.UserDTO;

public interface UserDAO {
  /**
   * 로그인 기능
   * select user_id , pwd, name from users where user_id=? and pwd=?
   * */
	UserDTO loginCheck(UserDTO userDTO)throws SQLException;
	
	/*
	 * UserNo를 통해 bid(입찰)테이블에 입찰중이던 회원인지 체크한다.
	 * null이면 insert into로 생성을 null이 아니면 update() 메소드를 한다.
	 * 
	 */
	BidDTO findBidByUserNo(int no) throws SQLException;
}
