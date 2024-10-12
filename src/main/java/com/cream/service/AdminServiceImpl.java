package com.cream.service;

import com.cream.dao.AdminDAO;
import com.cream.dao.AdminDAOImpl;
import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService {

    AdminDAO adminDAO = new AdminDAOImpl();

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
    }

    @Override
    public int updateSalesStatus(int salesNo, int salesStatus) throws SQLException {
        return adminDAO.updateSalesStatus(salesNo, salesStatus); // grade 파라미터 제거
    }

    @Override
    public int updateSalesGrade(int salesNo, char grade) throws SQLException {
        return adminDAO.updateSalesGrade(salesNo, grade); // grade 업데이트를 위한 메서드
    }

    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException {
        return adminDAO.submitSurvey(surveyData);
    }

    @Override
    public Map<String, String> getProductName(int productNo) throws SQLException {
        return adminDAO.getProductName(productNo); // DAO에서 productName을 가져오는 로직 호출
    }
}
