package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dao.UserDAOImpl;
import com.cream.dto.BidDTO;
import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;

public class UserServiceImpl implements UserService {
	UserDAOImpl dao = new UserDAOImpl();

	@Override
	public UserDTO register(UserDTO user) throws SQLException, AuthenticationException {
		
		return user;
	}

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

	@Override
	public int updateCash(String user_Id, int cash) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserRank(String user_Id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleUser(String user_Id, String pwd) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(UserDTO user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDTO selectUserById(int user_no) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addToWishlist(int user_no, int product_no) throws SQLException {
		int result = dao.addToWishlist(user_no, product_no);
		return result;
	}

	@Override
	public List<ProductDTO> selectWishlist(int user_no) throws SQLException {
		List<ProductDTO> list = dao.selectWishlist(user_no);
		return list;
	}

	@Override
	public int deleteWishlist(int user_no, int product_no) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSales(SalesDTO sales) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
}
