package com.cream.service;

import java.sql.SQLException;

import com.cream.dao.ProductDAO;
import com.cream.dao.ProductDAOImpl;
import com.cream.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;



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
	public ProductDTO searchByProductId(int productId) throws SQLException {
		// 상품 ID로 검색
		ProductDTO product = productDao.searchByProductId(productId);
		return product;
	}

	@Override
	public List<ProductDTO> searchProductKor(String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> searchProductEng(String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
