package com.model2.mvc.service.product;

import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.dao.ProductDao;

public interface ProductService{
	
	public void setProductDao(ProductDao productDao);
	
	public int addProduct(Product productVO) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public List<Object> getProductList(SearchVO searchVO) throws Exception;
	
	public int updateProduct(Product productVO) throws Exception;
	
	public int getTotalCount(SearchVO search) throws Exception;
	
	public List<String> getAutocompleteList(String keyword) throws Exception;
	
}