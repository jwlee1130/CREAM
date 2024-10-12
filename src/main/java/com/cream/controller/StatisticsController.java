package com.cream.controller;

import com.cream.service.StatisticService;
import com.cream.service.StatisticsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

public class StatisticsController {

    StatisticService statisticService = new StatisticsServiceImpl();

    public Map<String, Integer> getTop3ItemsByGender(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String gender = req.getParameter("gender");
        int period = Integer.parseInt(req.getParameter("period"));

        return statisticService.getTop3ItemsByGender(gender, period);
    }

    public Map<String, Integer> getTotalSalesData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int period = Integer.parseInt(request.getParameter("period"));

        return statisticService.getTotalSalesData(period);
    }

    public Map<String, Integer> getTop3BrandsFromSurvey(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return statisticService.getTop3BrandsFromSurvey();
    }

    public Map<String, Integer> getSalesData(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        int period = Integer.parseInt(request.getParameter("period"));

        return statisticService.getSalesData(productNo, period);
    }
}
