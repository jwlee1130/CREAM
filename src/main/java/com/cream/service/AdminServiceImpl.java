package com.cream.service;

import com.cream.dao.AdminDAO;
import com.cream.dao.AdminDAOImpl;
import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService {

    AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public int deleteUserByNo(int userNo) throws SQLException {
        return adminDAO.deleteUserByNo(userNo);
    }

    @Override
    public int deleteUsersSalesByNo(int salesNo) throws SQLException {
        return adminDAO.deleteUsersSalesByNo(salesNo);
    }

    @Override
    public List<SalesDTO> getUnapprovedProducts() throws SQLException {
        return adminDAO.getUnapprovedProducts();
    }

    @Override
    public int updateSalesStatus(int salesNo, int salesStatus, int price) throws SQLException {
        return adminDAO.updateSalesStatus(salesNo, salesStatus, price);
    }

    @Override
    public int updateSalesGrade(int salesNo, char grade) throws SQLException {
        return adminDAO.updateSalesGrade(salesNo, grade); 
    }

    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException {
        return adminDAO.submitSurvey(surveyData);
    }

    @Override
    public Map<String, String> getProductName(int productNo) throws SQLException {
        return adminDAO.getProductName(productNo);
    }

    @Override
    public boolean hasUserCompletedSurvey(String userId) throws SQLException
    {
        return adminDAO.hasUserCompletedSurvey(userId);
    }

    @Override
    public boolean isAdmin(String adminId) throws SQLException {
        return adminDAO.isAdmin(adminId);
    }

    @Override
    public ProductDTO getProduct(int categoryNo, int brandNo, int colorNo, int releasePrice) throws SQLException {
        return adminDAO.getProduct(categoryNo, brandNo, colorNo, releasePrice);
    }
}
