package com.cream.dao;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;
import com.cream.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public int deleteUserById(int userNo) throws SQLException {
        String sql="delete from users where user_no=?";
        Connection conn=null;
        PreparedStatement ps=null;

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,userNo);
            return ps.executeUpdate(); // delete 의 결과를 반환
        }
        finally
        {
            DbUtil.dbClose(conn,ps);
        }
    }

    @Override
    public int deleteProductById(int productNo) throws SQLException {
        String sql="delete from product where id=?";
        Connection conn=null;
        PreparedStatement ps=null;

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,productNo);
            return ps.executeUpdate();
        }
        finally
        {
            DbUtil.dbClose(conn,ps);
        }
    }

    @Override
    public List<SalesDTO> getUnapprovedProducts() throws SQLException {
        String sql="select * from users_sales where sales_status=0";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<SalesDTO> list=new ArrayList<SalesDTO>();

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next())
            {
                SalesDTO product=new SalesDTO();

                product.setNo(rs.getInt("no"));
                product.setUserNo(rs.getInt("user_no"));
                product.setProductNo(rs.getInt("product_no"));
                product.setStartingPrice(rs.getInt("starting_price"));
                product.setNowPrice(rs.getInt("now_price"));
                product.setSalesStatus(rs.getInt("sales_status"));
                product.setRegdate(rs.getString("regdate"));
                product.setGrade(rs.getString("grade").charAt(0));
                list.add(product);
            }
        }
        finally
        {
            DbUtil.dbClose(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int updateSalesStatus(int sales_no, int sales_status, char grade) throws SQLException {
        String sql="update users_sales set sales_status=?,grade=? where no=?";
        Connection conn=null;
        PreparedStatement ps=null;

        try
        {
            conn= DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,sales_status);
            ps.setString(2, String.valueOf(grade));
            ps.setInt(3,sales_no);

            return ps.executeUpdate();
        }
        finally
        {
            DbUtil.dbClose(conn,ps);
        }
    }

    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException {
        String sql="insert into survey values(user_no,category,brand,color,price) values (?,?,?,?,?)";

        Connection conn=null;
        PreparedStatement ps=null;

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,surveyData.getUserNo());
            ps.setString(2,surveyData.getCategory());
            ps.setString(3,surveyData.getBrand());
            ps.setString(4,surveyData.getColor());
            ps.setInt(5,surveyData.getPrice());
            return ps.executeUpdate();
        }
        finally
        {
            DbUtil.dbClose(conn,ps);
        }
    }
}
