 package com.cream.service;

 import java.sql.SQLException;
import java.util.List;

import com.cream.dto.AdminDTO;
import com.cream.dto.BidDTO;
import com.cream.dto.NotifyDTO;
import com.cream.dto.ProductViewDTO;
import com.cream.dto.RankDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SalesViewDTO;
import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;

public interface UserService {
	/**
	 * 회원가입
	 */
	 int register(UserDTO user) throws SQLException, AuthenticationException;
	
	/**
	 * 로그인 체크
	 * */
   UserDTO loginCheck(UserDTO userDTO)throws SQLException;
   
   AdminDTO loginAdminCheck(String adminId, String adminPw) throws SQLException;
   
   BidDTO findBidByUserNo(int no)throws SQLException , AuthenticationException;
	
	/**
	 * 	현금 충전
	 * */
	int updateCash(int user_no,int cash) throws SQLException;
	
	/**
	 * 	회원등급 확인
	 * */
	RankDTO getUserRank(String user_no) throws SQLException;
	
	/** 
	 * 	회원 탈퇴
	 * */
	int deleteUser(String user_Id,String pwd) throws SQLException;
	
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
	List<ProductViewDTO> selectWishlist(int user_no) throws SQLException;
	
	/**
	 * 	찜 삭제
	 * */
	int deleteWishlist(int user_no, int product_no) throws SQLException;

	// 누락된 항목 있음->노션 보고 추가하기
	
	/**
	 * 	판매등록 조회
	 * */
	List<SalesViewDTO> salesByUserNo(int user_no) throws SQLException;
	
	/**
	 * 	판매 등록
	 * */
	int insertSales(SalesDTO sales) throws SQLException;

	/*
	 * 알림 리스트 갖고오기
	 */
	List<NotifyDTO> getNotifyList(int user_no) throws SQLException;
	
	/*
	 * 알림 읽으면 update
	 */
	int updateNotify(int userNo, int notifyNo) throws SQLException;
	/*
	 * 알림 삭제버튼
	 */
	int deleteNotify(int userNo, int notifyNo) throws SQLException;

	/**
	 * 	관심상품 추가 및 해제
	 * */
	boolean isProductInWishlist(int userNo, int productNo) throws SQLException;

	int updateEmail(int userNo, String email) throws SQLException;
	
    int updatePassword(int userNo, String password) throws SQLException;
    
    int updateNickname(int userNo, String nickname) throws SQLException;
    
    int updatePhone(int userNo, String phone) throws SQLException;
    
    int updateShoeSize(int userNo, int shoeSize) throws SQLException;
    
    int updateAddress(int userNo, String address) throws SQLException;
}
