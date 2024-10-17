package com.cream.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dao.ProductDAOImpl;
import com.cream.dto.BrandDTO;
import com.cream.dto.ProductDTO;



public class ProductServiceImpl implements ProductService {
	ProductDAOImpl productDao = new ProductDAOImpl();
	List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
	
	public ProductDTO detail(int productNo) throws SQLException {
		ProductDTO product = productDao.detail(productNo);
		
		if(product==null)
			throw new SQLException("해당 상품이 존재하지 않습니다.");
		return product;
	}

	
	@Override
	public List<ProductDTO> selectAllProduct() throws SQLException {
		//전체 상품 검색
		productList = productDao.selectAllProduct();
		return productList;
	}
	
	@Override
	public ProductDTO searchByProductId(String productId) throws SQLException {
		// 상품 ID로 검색
		ProductDTO product = productDao.searchByProductId(productId);
		return product;
	}

	@Override
	public List<ProductDTO> searchProductKor(String keyword) throws SQLException {
		// 검색어가 한글인 경우
		productList = productDao.searchProductKor(keyword);
		return productList;
	}

	@Override
	public List<ProductDTO> searchProductEng(String keyword) throws SQLException {
		// 검색어가 영어인 경우
		productList = productDao.searchProductEng(keyword);
		return productList;
	}

	@Override
	public List<ProductDTO> searchProductByCategory(String productCategory) throws SQLException {
		// 상품 카테고리로 검색(운동화, 슬리퍼, 구두)
		productList = productDao.searchProductByCategory(productCategory);
		return productList;
	}
	
	@Override
	public List<ProductDTO> searchProductByBrand(String productBrand) throws SQLException {
		// 상품 브랜드 검색(나이키, 아디다스, 퓨마, 조던, 구찌, 에르메스)
		productList = productDao.searchProductByBrand(productBrand);
		return productList;
	}

	@Override
	public List<ProductDTO> searchProductByFilter(String[] categoryArr, String[] brandArr) throws SQLException {
		// 상품 필터로 검색
		productList = productDao.searchProductByFilter(categoryArr, brandArr);
		return productList;
	}
	
	public int getRecentPrice(int productNo)throws SQLException {
		return productDao.getRecentPrice(productNo);
	}


	public int getBidPricing(int productNo) throws SQLException {
		return productDao.getBidPricing(productNo);
	}


	public int getNowPricing(int productNo) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getNowPricing(productNo);
	}


}
