package com.cream.service;

import com.cream.dto.PurchaseDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

    Map<String, Integer> getTop3ItemsByGender(String gender, int period) throws SQLException;

    Map<String, Integer> getTotalSalesData(int period) throws SQLException;

    Map<String, Integer> getTop3BrandsFromSurvey() throws SQLException;

    Map<String, Integer> getTop3ColorsFromSurvey() throws SQLException;

    Map<String, Integer> getSalesData(int productNo, int period) throws SQLException;

    List<PurchaseDTO> getPurchaseData(int productNo, int period) throws SQLException;
}
