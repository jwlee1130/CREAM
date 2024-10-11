package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dao.AdminDAO;
import com.cream.dao.AdminDAOImpl;
import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

public class AdminServiceImpl implements AdminService {

    AdminDAO adminDAO=new AdminDAOImpl();

    @Override
    public int deleteUserById(int userNo) throws SQLException
    {
        return adminDAO.deleteUserById(userNo);
    }

    @Override
    public int deleteProductById(int productNo) throws SQLException
    {
        return adminDAO.deleteProductById(productNo);
    }

    @Override
    public List<SalesDTO> getUnapprovedProducts() throws SQLException
    {
        return adminDAO.getUnapprovedProducts();
    }

    @Override
    public int updateSalesStatus(int salesNo, int salesStatus, char grade) throws SQLException
    {
        return adminDAO.updateSalesStatus(salesNo, salesStatus, grade);
    }

    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException
    {
        return adminDAO.submitSurvey(surveyData);
    }
}
