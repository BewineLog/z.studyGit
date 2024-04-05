package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;


@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao productDao;
	
	public void setProductDao(ProductDao productDao){
		this.productDao = productDao;
	}
	
	public int addProduct(Product productVO) throws Exception{
		return productDao.addProduct(productVO);
	}
	
	
	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}
	
	public List<Object> getProductList(SearchVO search) throws Exception {
		
		return productDao.getProductList(search);
	}
	
	
	public int updateProduct(Product productVO) throws Exception{
		return productDao.updateProduct(productVO);
	}
	
	public int getTotalCount(SearchVO search) throws Exception {
		return productDao.getTotalCount(search);
	}
	
	public List<String> getAutocompleteList(String keyword) throws Exception{
		return productDao.getAutocompleteList(keyword);
	}
	
}
