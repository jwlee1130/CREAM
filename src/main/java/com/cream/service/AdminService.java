package com.cream.service;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    int deleteUserById(int userNo) throws SQLException;

    int deleteProductById(int productNo) throws SQLException;

    List<SalesDTO> getUnapprovedProducts() throws SQLException;

    int updateSalesStatus(int salesNo, int salesStatus, String grade) throws SQLException;

    int submitSurvey(SurveyDTO surveyData) throws SQLException;
}
