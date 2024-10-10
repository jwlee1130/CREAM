package com.cream.dao;

import java.sql.SQLException;
import java.util.Map;

public interface StatisticsDAO {
	/**
	 * 	카테고리, 기간에 따라 인깅 품목 3개 이름과 판매량
	 * */
	Map<String, Integer> getTop3Items(String category, String period) throws SQLException;
	
	/**
	 * 	구매 페이지에서 시세를 알기 위해 날짜 & 가격 얻어
	 * */
	Map<String,Integer> getSalesData(int product_no, String period) throws SQLException;
}
