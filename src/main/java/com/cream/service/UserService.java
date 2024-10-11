 package com.cream.service;

 import com.cream.dto.BidDTO;
import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.UserDTO;
 import com.cream.exception.AuthenticationException;

 import java.sql.SQLException;
import java.util.List;

public interface UserService {
	/**
	 * 회원가입
	 */
	 UserDTO register(UserDTO user) throws SQLException, AuthenticationException;
	
	/**
	 * 로그인 체크
	 * */
   UserDTO loginCheck(UserDTO userDTO)throws SQLException , AuthenticationException;
   
   BidDTO findBidByUserNo(int no)throws SQLException , AuthenticationException;
	
	/**
	 * 	현금 충전
	 * */
	int updateCash(String user_Id,int cash) throws SQLException;
	
	/**
	 * 	회원등급 확인
	 * */
	String getUserRank(String user_Id) throws SQLException;
	
	/**
	 * 	회원 탈퇴
	 * */
	int deleUser(String user_Id,String pwd) throws SQLException;
	
	/**
	 * 	회원정보 수정
	 * */
	int updateUser(UserDTO user) throws SQLException;
	
	/**
	 * 	회원정보 조회
	 * */
	UserDTO selectUserById(int user_no) throws SQLException;
	
	/**
	 * 	찜추가
	 * */
	int addToWishlist(int user_no, int product_no) throws SQLException;
	
	/**
	 * 	찜 조회
	 * */
	List<ProductDTO> selectWishlist(int user_no) throws SQLException;
	
	/**
	 * 	찜 삭제
	 * */
	int deleteWishlist(int user_no, int product_no) throws SQLException;

	// 누락된 항목 있음->노션 보고 추가하기

	/**
	 * 	판매 등록
	 * */
	int insertSales(SalesDTO sales) throws SQLException;
}
