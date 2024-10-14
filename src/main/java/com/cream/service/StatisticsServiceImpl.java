package com.cream.service;

import com.cream.dao.StatisticsDAO;
import com.cream.dao.StatisticsDAOImpl;
import com.cream.dto.PurchaseDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StatisticsServiceImpl implements StatisticsService {

    StatisticsDAO statisticsDAO = new StatisticsDAOImpl();

    @Override
    public Map<String, Integer> getTop3ItemsByGender(String gender, int period) throws SQLException {
        return statisticsDAO.getTop3ItemsByGender(gender, period);
    }

    @Override
    public Map<String, Integer> getTotalSalesData(int period) throws SQLException {
        return statisticsDAO.getTotalSalesData(period);
    }

    @Override
    public Map<String, Integer> getTop3BrandsFromSurvey() throws SQLException {
        return statisticsDAO.getTop3BrandsFromSurvey();
    }

    @Override
    public Map<String, Integer> getTop3ColorsFromSurvey() throws SQLException {
        return statisticsDAO.getTop3ColorsFromSurvey();
    }

    @Override
    public Map<String, Integer> getSalesData(int productNo, int period) throws SQLException {
        return statisticsDAO.getSalesData(productNo, period);
    }

    @Override
    public List<PurchaseDTO> getPurchaseData(int productNo, int period) throws SQLException {
        return statisticsDAO.getPurchaseData(productNo,period);
    }
}
