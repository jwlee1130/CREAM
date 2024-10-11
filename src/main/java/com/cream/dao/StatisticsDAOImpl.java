package com.cream.dao;

import com.cream.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatisticsDAOImpl implements StatisticsDAO
{

    @Override
    public Map<String, Integer> getTop3ItemsByGender(String gender, int period) throws SQLException
    {
       String sql="select p.eng_name, count(pur.product_no) as total_sales "+
       "from purchase pur "+
               "join product p on pur.product_no=p.no "+
               "join users u on pur.user_no=u.no"+
               "where u.gender = ? "+
               "and pur.regdate >= date_sub(curdate(),interval ? day) "+
               "group by p.eng_name "+
               "order by total_sales desc"+
               "limit 3";

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

    @Override
    public Map<String, Integer> getSalesData(int productNo, int period) throws SQLException
    {
        String sql="select regdate,sum(price) as daily_sales "+
                "from purchases "+
                "where productNo=? and regdate>=date_sub(curdate(),interval ? day)"+
                "group by regdate"+
                "order by regdate asc";

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

    @Override
    public Map<String, Integer> getTotalSalesData(int period) throws SQLException
    {
        String sql = "SELECT SUM(price) AS total_sales " +
                "FROM purchase " +
                "WHERE regdate >= DATE_SUB(CURDATE(), INTERVAL ? DAY)";

        Map<String, Integer> salesData = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, period);
            rs = ps.executeQuery();

            if (rs.next())
            {
                salesData.put("total_sales", rs.getInt("total_sales"));
            }
        }
        finally
        {
            DbUtil.dbClose(conn, ps, rs);
        }

        return salesData;
    }

    @Override
    public Map<String, Integer> getTop3BrandsFromSurvey() throws SQLException
    {
        String sql="select s.brand,count(s.brand) as pop "+
                "from survey s "+
                "group by s.brand "+
                "order by pop desc "+
                "limit 3";

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

    @Override
    public Map<String, Integer> getTop3ColorsFromSurvey() throws SQLException {
        // SQL 쿼리: 색상별 선택 횟수를 계산하여 상위 3개의 인기 색상을 가져옴
        String sql = "SELECT s.color, COUNT(s.color) AS popularity " +
                "FROM survey s " +
                "GROUP BY s.color " +
                "ORDER BY popularity DESC " +
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
}