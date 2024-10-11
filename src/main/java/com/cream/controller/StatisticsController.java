package com.cream.controller;

import com.cream.service.StatisticService;
import com.cream.service.StatisticsServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

public class StatisticsController implements RestController {

    StatisticService statisticService=new StatisticsServiceImpl();
    Gson gson=new Gson();

    public void getTop3ItemsByGender(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException
    {
        String gender=req.getParameter("gender");
        int period=Integer.parseInt(req.getParameter("period"));

        Map<String,Integer> topItems=statisticService.getTop3ItemsByGender(gender,period);

        String jsonResponse=gson.toJson(topItems);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out=resp.getWriter();
        out.print(jsonResponse);
    }

    public void getTotalSalesData(HttpServletRequest request,HttpServletResponse response)throws IOException, SQLException
    {
        int period=Integer.parseInt(request.getParameter("period"));

        Map<String,Integer> totalSales=statisticService.getTotalSalesData(period);

        String jsonResponse=gson.toJson(totalSales);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonResponse);
    }

    public void getTop3BrandsFromSurvey(HttpServletResponse request,HttpServletResponse response) throws IOException, SQLException
    {
        Map<String,Integer> top3Brands=statisticService.getTop3BrandsFromSurvey();

        String jsonResponse=gson.toJson(top3Brands);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonResponse);
    }

    public void getSalesData(HttpServletRequest request,HttpServletResponse response)throws IOException, SQLException
    {
        int productNo=Integer.parseInt(request.getParameter("productNo"));
        int period=Integer.parseInt(request.getParameter("period"));

        Map<String,Integer> salesData=statisticService.getSalesData(productNo,period);

        String jsonResponse=gson.toJson(salesData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonResponse);
    }

}
