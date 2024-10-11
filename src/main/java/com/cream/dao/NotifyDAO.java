package com.cream.dao;

import com.cream.dto.NotifyDTO;

import java.sql.SQLException;
import java.util.List;

public interface NotifyDAO {
	/**
	 * 	알람 보내기
	 * */
	int insertNotify(NotifyDTO notify) throws SQLException;
	
	/**
	 * 	알람 보여주기
	 * */
    List<NotifyDTO> sendNotify(int user_no) throws SQLException;
    
    /**
     * 	알람 삭제
     * */
    int deleteNotify(int user_no)throws SQLException;

    /**
     * 	알람 확인여부
     * */
    int updateNotify(int user_no, int notify_no) throws SQLException;
}