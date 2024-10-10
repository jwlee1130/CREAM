package com.cream.service;

import com.cream.dao.AdminDAO;
import com.cream.dao.AdminDAOImpl;
import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    AdminDAO adminDAO=new AdminDAOImpl();

    @Override
    public int deleteUserById(int userNo) throws SQLException {
        return adminDAO.deleteUserById(userNo);
    }

    @Override
    public int deleteProductById(int productNo) throws SQLException {
        return adminDAO.deleteProductById(productNo);
    }

    @Override
    public List<SalesDTO> getUnapprovedProducts() throws SQLException {
        return adminDAO.getUnapprovedProducts();
    }// 검수 받지 못한 상품 목록들 확인

    @Override
    public int updateSalesStatus(int salesNo, int salesStatus, char grade) throws SQLException {
        return adminDAO.updateSalesStatus(salesNo, salesStatus, grade);
    }// 업데이트 결과 반환

    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException {
        return adminDAO.submitSurvey(surveyData);
    }// 제출 결과 반환
}
