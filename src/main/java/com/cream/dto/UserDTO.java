package com.cream.dto;

import com.google.gson.annotations.Expose;

public class UserDTO {
	@Expose private int no;
	@Expose private int rankNo;
	@Expose private String userId;
	@Expose private String name;
	@Expose private String userEmail;
	@Expose private String userPw;
	@Expose private String hp;
	@Expose private String nickname;
	@Expose private int shoesSize;
	@Expose private String regdate;
	@Expose private int cash;
	@Expose private String gender;
	@Expose private int age;
	@Expose private String address;
   
   
   public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDTO() {}
   
	public UserDTO(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
		
	}
   
	public UserDTO(String userId, String userPw, String nickname) {
	this.userId = userId;
	this.userPw = userPw;
	this.nickname = nickname;
	}
	
	

	public UserDTO(int no, int rankNo, String userId, String name, String userEmail, String userPw, String hp,
			String nickname, int shoesSize, String regdate, int cash, String gender, int age, String address) {
		this(userId,userPw,nickname);
		this.no = no;
		this.rankNo = rankNo;
		this.name = name;
		this.userEmail = userEmail;
		this.hp = hp;
		this.shoesSize = shoesSize;
		this.regdate = regdate;
		this.cash = cash;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getRankNo() {
		return rankNo;
	}
	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getShoesSize() {
		return shoesSize;
	}
	public void setShoesSize(int shoesSize) {
		this.shoesSize = shoesSize;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
