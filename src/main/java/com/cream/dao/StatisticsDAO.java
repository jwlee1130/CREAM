package com.cream.dao;

import com.cream.dto.PurchaseDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StatisticsDAO {
	/**
	 * <관리자 마이페이지>
	 * 남,녀 인기품목 3가지씩
	 * gender 에 찾고자 하는 남,녀가 들어감
	 */
	Map<String,Integer> getTop3ItemsByGender(String gender,int period) throws SQLException;

	/**
	 * <관리자 마이페이지>
	 * 일정 기간 동안의 전체 매출액
	 * 오늘로부터 며칠 전까지 볼지 period 로 정한다
	 */
	Map<String,Integer> getTotalSalesData(int period) throws SQLException;

	/**
	 * <관리자 마이페이지>
	 *  설문조사에서 가장 인기 많았던 브랜드 3개를 도넛 그래프에 반영하기 위해 인기 많은 3가지 브랜드 가지고 오는 함수
	 */
	Map<String,Integer> getTop3BrandsFromSurvey() throws SQLException;

	/**
	 * <관리자 마이페이지>
	 *  설문조사에서 가장 인기 많았던 색깔 3개 도넛 그래프에 반영
	 */
	Map<String,Integer> getTop3ColorsFromSurvey() throws SQLException;

	/**
	 * 	<구매 페이지>
	 * 	구매 페이지에서 시세를 알기 위해 날짜 & 가격 얻어
	 * 	period 변수를 사용해서 오늘로부터 며칠 전까지 판매가격을 볼지 정하고, 해당 기간의 가격과 그에 대한 날짜를 map 으로 반환
	 * */
	Map<String,Integer> getSalesData(int productNo, int period) throws SQLException;

	/**
	 * 	<구매 페이지>
	 * 	특정 제품 번호와 기간에 대한 구매 데이터를 리스트로 반환
	 */
	List<PurchaseDTO> getPurchaseData(int productNo, int period) throws SQLException;

}
