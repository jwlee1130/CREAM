package com.cream.dao;

import com.cream.dto.NotifyDTO;

import java.sql.SQLException;
import java.util.List;

public interface NotifyDAO {
    List<NotifyDTO> sendNotify(int user_no) throws SQLException;

    int deleteNotify(int user_no)throws SQLException;


}
