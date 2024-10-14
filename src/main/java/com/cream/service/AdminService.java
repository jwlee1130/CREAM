package com.cream.service;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    int deleteUserById(int userNo) throws SQLException;// ajax 로 처리

    int deleteProductById(int productNo) throws SQLException;// ajax 로 처리

    List<SalesDTO> getUnapprovedProducts() throws SQLException;// ajax 로 처리?

    int updateSalesStatus(int salesNo, int salesStatus, char grade) throws SQLException;// ajax 로 처리

    int submitSurvey(SurveyDTO surveyData) throws SQLException;// ajax 로 처리
}
