package com.cream.service;

import java.sql.SQLException;
import java.util.Map;

public interface StatisticService {

    Map<String, Integer> getTop3ItemsByGender(String gender, int period) throws SQLException;

    Map<String, Integer> getTotalSalesData(int period) throws SQLException;

    Map<String, Integer> getTop3BrandsFromSurvey() throws SQLException;

    Map<String, Integer> getTop3ColorsFromSurvey() throws SQLException;

    Map<String, Integer> getSalesData(int productNo, int period) throws SQLException;


}
