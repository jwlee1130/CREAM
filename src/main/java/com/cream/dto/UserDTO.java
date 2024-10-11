package com.cream.dto;

import java.sql.Date;

public class UserDTO {
	private int no;
    private int rankNo;
    private String userId;
    private String Name;
    private String userEmail;
    private String userPw;
    private String hp;
    private String nickname;
    private int shoesSize;
    private java.sql.Date regdate;
    private int cash;
    private String gender;
    private int age;
    private String address;
   
   
   public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
public UserDTO() {}
   public UserDTO(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		
	}
   
	public UserDTO(String userId, String userPw, String nickname) {
	super();
	this.userId = userId;
	this.userPw = userPw;
	this.nickname = nickname;
}
	public UserDTO(int no, int rankNo, String userId,String Name, String userEmail, String userPw, String hp, String nickname,
			int shoesSize, Date regdate, int cash, String gender, int age, String address) {
		super();
		this.no = no;
		this.rankNo = rankNo;
		this.userId = userId;
		this.Name = Name;
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.hp = hp;
		this.nickname = nickname;
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
	public java.sql.Date getRegdate() {
		return regdate;
	}
	public void setRegdate(java.sql.Date regdate) {
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
