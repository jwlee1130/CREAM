package com.cream.dao;

import com.cream.dto.PurchaseDTO;
import com.cream.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsDAOImpl implements StatisticsDAO
{

    /*
    SELECT p.eng_name, COUNT(pur.product_no) AS TOTAL_SALES
    FROM PURCHASE pur
    JOIN PRODUCT p ON pur.product_no = p.no
    JOIN USERS u ON pur.buy_user_no = u.no
    WHERE u.gender = '여'
    AND pur.regdate >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
    GROUP BY p.eng_name
    ORDER BY TOTAL_SALES DESC
    LIMIT 3;


     */
    @Override
    public Map<String, Integer> getTop3ItemsByGender(String gender, int period) throws SQLException
    {
        String sql = "SELECT p.eng_name, COUNT(pur.product_no) AS TOTAL_SALES " +
                "FROM PURCHASE pur " +
                "JOIN PRODUCT p ON pur.product_no = p.no " +
                "JOIN USERS u ON pur.buy_user_no = u.no " +  // pur.user_no -> pur.buy_user_no 로 수정(디비 변경)
                "WHERE u.gender = ? " +
                "AND pur.regdate >= DATE_SUB(CURDATE(), INTERVAL ? DAY) " +
                "GROUP BY p.eng_name " +
                "ORDER BY TOTAL_SALES DESC " +
                "LIMIT 3";


        Map<String,Integer> topItems=new HashMap<>();

       Connection conn=null;
       PreparedStatement ps=null;
       ResultSet rs=null;

       try
       {
           conn=DbUtil.getConnection();
           ps=conn.prepareStatement(sql);
           ps.setString(1,gender);
           ps.setInt(2,period);

           rs=ps.executeQuery();

           while(rs.next())
           {
               String itemName=rs.getString("eng_name");
               int totalSales=rs.getInt("total_sales");
               topItems.put(itemName,totalSales);
           }
       }
       finally
       {
           DbUtil.dbClose(conn,ps,rs);
       }
       return topItems;
    }

    /*
    SELECT regdate, SUM(price) AS DAILY_SALES
    FROM PURCHASE
    WHERE product_no = 1 AND regdate >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
    GROUP BY regdate
    ORDER BY regdate ASC;

     */
    @Override
    public Map<String, Integer> getSalesData(int productNo, int period) throws SQLException
    {
        String sql = "SELECT regdate, SUM(price) AS DAILY_SALES " +
                "FROM PURCHASE " +
                "WHERE product_no = ? AND regdate >= DATE_SUB(CURDATE(), INTERVAL ? DAY) " +
                "GROUP BY regdate " +
                "ORDER BY regdate ASC";


        Map<String,Integer> salesData=new HashMap<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try
        {
            conn= DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,productNo);
            ps.setInt(2,period);

            rs=ps.executeQuery();

            while(rs.next())
            {
                String regDate=rs.getString("regdate");
                int dailySales=rs.getInt("daily_sales");
                salesData.put(regDate,dailySales);
            }
        }
        finally
        {
            DbUtil.dbClose(conn,ps,rs);
        }
        return salesData;

    }

    /*
    SELECT DATE(regdate) AS sale_date, SUM(price) AS total_sales
    FROM PURCHASE
    WHERE regdate >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
    AND regdate <= CURDATE() -- 현재 날짜까지의 범위를 추가
    GROUP BY DATE(regdate);

     */
    @Override
    public Map<String, Integer> getTotalSalesData(int period) throws SQLException {
        String sql = "SELECT DATE(regdate) AS sale_date, SUM(price) AS total_sales " +
                "FROM PURCHASE " +
                "WHERE regdate >= DATE_SUB(CURDATE(), INTERVAL ? DAY) " +
                "AND regdate <= CURDATE() " +
                "GROUP BY DATE(regdate)";

        Map<String, Integer> salesData = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, period);
            rs = ps.executeQuery();

            while (rs.next()) {
                String date = rs.getString("sale_date");
                int totalSales = rs.getInt("total_sales");
                salesData.put(date, totalSales);
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }

        return salesData;
    }


    /*
    SELECT s.brand, COUNT(s.brand) AS pop
    FROM SURVEY s
    GROUP BY s.brand
    ORDER BY pop DESC
    LIMIT 3;
     */

    @Override
    public Map<String, Integer> getTop3BrandsFromSurvey() throws SQLException
    {
        String sql = "SELECT s.brand, COUNT(s.brand) AS pop " +
                "FROM SURVEY s " +
                "GROUP BY s.brand " +
                "ORDER BY pop DESC " +
                "LIMIT 3";


        Map<String,Integer> topBrands=new HashMap<>();

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next())
            {
                String brand=rs.getString("brand");
                int pop=rs.getInt("pop");
                topBrands.put(brand,pop);
            }
        }
        finally
        {
            DbUtil.dbClose(conn,ps,rs);
        }
        return topBrands;

    }


    /*
    SELECT s.color, COUNT(s.color) AS popularity
    FROM SURVEY s
    GROUP BY s.color
    ORDER BY popularity DESC
    LIMIT 3;
     */
    @Override
    public Map<String, Integer> getTop3ColorsFromSurvey() throws SQLException {
        String sql = "SELECT s.color, COUNT(s.color) AS POPULARITY " +
                "FROM SURVEY s " +
                "GROUP BY s.color " +
                "ORDER BY POPULARITY DESC " +
                "LIMIT 3";


        Map<String, Integer> topColors = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next())
            {
                String color = rs.getString("color");
                int popularity = rs.getInt("popularity");
                topColors.put(color, popularity);
            }
        }
        finally
        {
            DbUtil.dbClose(conn, ps, rs);
        }

        return topColors;
    }

    /*
    SELECT PRODUCT_NO, PRICE, REGDATE, ADDRESS, SALES_USER_NO, BUY_USER_NO
    FROM PURCHASE
    WHERE PRODUCT_NO = 1
    AND REGDATE >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
    ORDER BY REGDATE ASC;
     */
    @Override
    public List<PurchaseDTO> getPurchaseData(int productNo, int period) throws SQLException {
        String sql = "SELECT PRODUCT_NO, PRICE, REGDATE, ADDRESS, SALES_USER_NO, BUY_USER_NO " +
                "FROM PURCHASE " +
                "WHERE PRODUCT_NO = ? " +
                "AND REGDATE >= DATE_SUB(CURDATE(), INTERVAL ? DAY) " +
                "ORDER BY REGDATE ASC ";

        List<PurchaseDTO> purchaseList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productNo);
            ps.setInt(2, period);

            rs = ps.executeQuery();

            while (rs.next()) {
                PurchaseDTO purchase = new PurchaseDTO();
                purchase.setProductNo(rs.getInt("PRODUCT_NO"));
                purchase.setPrice(rs.getInt("PRICE"));
                purchase.setRegdate(rs.getString("REGDATE"));
                purchase.setAddress(rs.getString("ADDRESS"));
//                purchase.setSalesUserNo(rs.getInt("SALES_USER_NO"));
//                purchase.setBuyUserNo(rs.getInt("BUY_USER_NO"));

                purchaseList.add(purchase);
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }

        return purchaseList;
    }

    /*
    SELECT p.*, b.BRAND, pi.FILE_PATH, pi.FILE_SIZE, pi.REGDATE
    FROM PRODUCT p
    JOIN BRAND b ON p.BRAND_NO = b.NO
    JOIN PRODUCT_IMG pi ON p.NO = pi.PRODUCT_NO AND p.COLOR_NO = pi.COLOR_NO
    WHERE p.CATEGORY_NO = 111
     AND p.BRAND_NO = 1000
    AND p.COLOR_NO = 101
     AND p.RELEASE_PRICE <= 1000000
   ORDER BY p.RELEASE_PRICE DESC
    LIMIT 1;

     */


}
